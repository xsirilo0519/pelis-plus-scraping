package co.com.sofka.peliculas.domain.cartelera.command;

import co.com.sofka.peliculas.domain.generic.Command;

public class AgregarPeliculaCommand extends Command {

    private String carteleraId;
    private String peliculaId;
    private String nombre;
    private String path;
    private String descripcion;
    private String categoria;

    public String getCarteleraId() {
        return carteleraId;
    }

    public void setCarteleraId(String carteleraId) {
        this.carteleraId = carteleraId;
    }

    public String getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(String peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
