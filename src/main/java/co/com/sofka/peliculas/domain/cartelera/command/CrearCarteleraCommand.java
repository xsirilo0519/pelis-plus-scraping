package co.com.sofka.peliculas.domain.cartelera.command;

import co.com.sofka.peliculas.domain.generic.Command;

import java.util.Date;

public class CrearCarteleraCommand extends Command {
    private String carteleraId;
    private Date date;

    public String getCarteleraId() {
        return carteleraId;
    }

    public void setCarteleraId(String carteleraId) {
        this.carteleraId = carteleraId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
