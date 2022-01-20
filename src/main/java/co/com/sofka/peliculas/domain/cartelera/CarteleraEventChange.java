package co.com.sofka.peliculas.domain.cartelera;

import co.com.sofka.peliculas.domain.cartelera.event.CarteleraCreada;
import co.com.sofka.peliculas.domain.cartelera.event.PeliculaAgregada;
import co.com.sofka.peliculas.domain.cartelera.event.PeliculasExtraidas;
import co.com.sofka.peliculas.domain.generic.EventChange;

import java.util.HashMap;

public class CarteleraEventChange implements EventChange {
    protected CarteleraEventChange(Cartelera cartelera){

        listener((CarteleraCreada event)->{
            cartelera.date= event.getDate();
            cartelera.peliculas= new HashMap<>();
        });

        listener((PeliculaAgregada event)->{
            cartelera.peliculas.put(event.getPeliculaId(),new Pelicula(event.getPeliculaId(),event.getNombre(),event.getPath(),event.getDescripcion(),event.getCategoria()));
        });

        listener((PeliculasExtraidas event)->{
            cartelera.peliculas.put(event.getPeliculaId(),new Pelicula(event.getPeliculaId(),event.getNombre(),event.getPath(),event.getDescripcion(),event.getCategoria()));
        });
    }
}
