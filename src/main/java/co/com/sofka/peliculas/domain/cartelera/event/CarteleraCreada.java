package co.com.sofka.peliculas.domain.cartelera.event;

import co.com.sofka.peliculas.domain.generic.DomainEvent;

public class CarteleraCreada extends DomainEvent {
    private final String date;

    public CarteleraCreada( String date) {
        super("sofka.campus.cartelera.carteleracreada");
        this.date=date;
    }


    public String getDate() {
        return date;
    }
}
