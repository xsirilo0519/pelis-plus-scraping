package co.com.sofka.peliculas.domain.cartelera.event;

import co.com.sofka.peliculas.domain.generic.DomainEvent;

public class CarteleraCreada extends DomainEvent {
    private final String carteleraId;
    private final String date;

    public CarteleraCreada(String carteleraId, String date) {
        super("sofka.campus.cartelera.carteleraCreada");
        this.carteleraId=carteleraId;
        this.date=date;
    }

    public String getCarteleraId() {
        return carteleraId;
    }

    public String getDate() {
        return date;
    }
}
