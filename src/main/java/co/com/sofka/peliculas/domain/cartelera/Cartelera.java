package co.com.sofka.peliculas.domain.cartelera;

import co.com.sofka.peliculas.domain.cartelera.event.CarteleraCreada;
import co.com.sofka.peliculas.domain.cartelera.event.PeliculaAgregada;
import co.com.sofka.peliculas.domain.cartelera.event.PeliculasExtraidas;
import co.com.sofka.peliculas.domain.generic.AggregateRoot;
import co.com.sofka.peliculas.domain.generic.DomainEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cartelera extends AggregateRoot {

    protected Map<String,Pelicula> peliculas;
    protected String date;

    public Cartelera(String carteleraId,String date) {
        super(carteleraId);
        subscribe(new CarteleraEventChange(this));
        this.peliculas=new HashMap<>();
        appendChange(new CarteleraCreada(date)).apply();
    }

    private Cartelera(String id) {
        super(id);
        subscribe(new CarteleraEventChange(this));
    }

    public static Cartelera from(String carteleraId, List<DomainEvent> events) {
        var cartelera=new Cartelera(carteleraId);
        events.forEach(cartelera::applyEvent);
        return cartelera;
    }

    public void addPelicula(String id, String nombre, String path, String descripcion, String categoria){
        appendChange(new PeliculaAgregada(id,nombre,path,descripcion,categoria)).apply();
    }

    public void extraerPelicula(String id, String nombre, String path, String descripcion, String categoria){
        appendChange(new PeliculasExtraidas(id,nombre,path,descripcion,categoria)).apply();
    }

    public Map<String, Pelicula> peliculas() {
        return peliculas;
    }

    public String date() {
        return date;
    }
}
