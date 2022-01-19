package co.com.sofka.peliculas.domain.cartelera;

import co.com.sofka.peliculas.domain.cartelera.event.CarteleraCreada;
import co.com.sofka.peliculas.domain.generic.AggregateRoot;
import co.com.sofka.peliculas.domain.generic.DomainEvent;
import co.com.sofka.peliculas.domain.generic.EventChange;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Cartelera extends AggregateRoot implements EventChange {

    protected Map<String,Pelicula> peliculas;
    protected Date date;

    public Cartelera(String carteleraId,Date date) {
        super(carteleraId);
        subscribe(new CarteleraEventChange(this));
        appendChange(new CarteleraCreada(carteleraId,date)).apply();
    }

    public Cartelera(String carteleraId) {
        super(carteleraId);
        subscribe(new CarteleraEventChange(this));
    }

    public static Cartelera from(String carteleraId, List<DomainEvent> events) {
        var cartelera=new Cartelera(carteleraId);
        events.forEach(cartelera::applyEvent);
        return cartelera;
    }

    public void addPelicula(String id, String nombre, String path, String descripcion, String categoria){
        peliculas.put(id,new Pelicula(id,nombre,path,descripcion,categoria));
        //appendChange(new Ca(id,name)).apply();
    }

    public Map<String, Pelicula> peliculas() {
        return peliculas;
    }
}
