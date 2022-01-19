package co.com.sofka.peliculas.domain.cartelera;

import co.com.sofka.peliculas.domain.cartelera.event.CarteleraCreada;
import co.com.sofka.peliculas.domain.generic.EventChange;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

public class CarteleraEventChange implements EventChange {
    protected CarteleraEventChange(Cartelera cartelera){
        listener((CarteleraCreada event)->{
            cartelera.date= event.getDate();
            cartelera.peliculas= new HashMap<>();
        });
    }
}
