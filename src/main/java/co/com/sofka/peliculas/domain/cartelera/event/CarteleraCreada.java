package co.com.sofka.peliculas.domain.cartelera.event;

import co.com.sofka.peliculas.domain.generic.DomainEvent;

import java.util.Date;

public class CarteleraCreada extends DomainEvent {
    private final String carteleraId;
    private final Date date;

    public CarteleraCreada(String carteleraId, Date date) {
        super("sofka.campus.cartelera.carteleraCreada");
        this.carteleraId=carteleraId;
        this.date=date;
    }

    public String getCarteleraId() {
        return carteleraId;
    }

    public Date getDate() {
        return date;
    }
}
