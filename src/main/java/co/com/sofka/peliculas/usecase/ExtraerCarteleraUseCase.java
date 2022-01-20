package co.com.sofka.peliculas.usecase;

import co.com.sofka.peliculas.domain.cartelera.Cartelera;
import co.com.sofka.peliculas.domain.cartelera.command.ExtraerPeliculasCommand;
import co.com.sofka.peliculas.domain.generic.DomainEvent;
import co.com.sofka.peliculas.domain.generic.EventStoreRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Dependent
public class ExtraerCarteleraUseCase implements Function<ExtraerPeliculasCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;
    final String baseURL = "https://pelisplus.so/estrenos";

    public ExtraerCarteleraUseCase(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(ExtraerPeliculasCommand command) {

        var cartelera = Cartelera.from(command.getCarteleraId(),
                repository.getEventsBy("cartelera", command.getCarteleraId()));

            var document = urlBase();
        for (Element carta : document.select(".items-peliculas .item-pelicula a")) {
            final String urlPelicula = carta.attr("href");

            try {
                    final Document movie = Jsoup.connect("https://pelisplus.so" + urlPelicula).get();
                    String id=movie.select(".info-content p:nth-of-type(1) span:nth-of-type(2)").text();
                    String nombre = movie.select(".info-content h1").text();
                    String path = movie.select(".player.player-normal ul:nth-of-type(2)  li:nth-of-type(1)").attr("data-video");
                    String descripcion = movie.select(".sinopsis").text();
                    String categoria = movie.select(".info-content p:nth-of-type(4) span:nth-of-type(2)").text();
                    System.out.println(id+" "+nombre+" "+path+" "+descripcion+" "+categoria);
                    cartelera.extraerPelicula(id,nombre,path,descripcion,categoria);

                } catch (Exception ex) {
                    throw new RuntimeException();
                }
            }
        return cartelera.getUncommittedChanges();
    }

    public Document urlBase(){
        try {
            return Jsoup.connect(baseURL).get();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}