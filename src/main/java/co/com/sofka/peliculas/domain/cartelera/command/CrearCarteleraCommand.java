package co.com.sofka.peliculas.domain.cartelera.command;

import co.com.sofka.peliculas.domain.generic.Command;

public class CrearCarteleraCommand extends Command {
    private String carteleraId;
    private String date;

    public CrearCarteleraCommand() {
    }

    public String getCarteleraId() {
        return carteleraId;
    }

    public void setCarteleraId(String carteleraId) {
        this.carteleraId = carteleraId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
