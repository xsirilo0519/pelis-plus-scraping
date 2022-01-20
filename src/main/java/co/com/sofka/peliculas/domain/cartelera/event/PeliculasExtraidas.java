package co.com.sofka.peliculas.domain.cartelera.event;

import co.com.sofka.peliculas.domain.generic.DomainEvent;

public class PeliculasExtraidas extends DomainEvent {

    private final String peliculaId;
    private final String nombre;
    private final String path;
    private final String descripcion;
    private final String categoria;

    public PeliculasExtraidas(String peliculaId, String nombre, String path, String descripcion, String categoria) {
        super("sofka.campus.cartelera.peliculasextraidas");
        this.peliculaId=peliculaId;
        this.nombre=nombre;
        this.path=path;
        this.descripcion=descripcion;
        this.categoria=categoria;
    }



    public String getPeliculaId() {
        return peliculaId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPath() {
        return path;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }
}
