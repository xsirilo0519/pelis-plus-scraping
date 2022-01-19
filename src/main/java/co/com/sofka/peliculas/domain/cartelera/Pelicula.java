package co.com.sofka.peliculas.domain.cartelera;

import java.util.Objects;

public class Pelicula {
    private final String id;
    private final String nombre;
    private final String path;
    private final String descripcion;
    private final String categoria;

    public Pelicula(String id, String nombre, String path, String descripcion, String categoria) {
        this.id = Objects.requireNonNull(id);
        this.nombre = Objects.requireNonNull(nombre);
        this.path = Objects.requireNonNull(path);
        this.descripcion = Objects.requireNonNull(descripcion);
        this.categoria = Objects.requireNonNull(categoria);
    }

    public String id() {
        return id;
    }

    public String nombre() {
        return nombre;
    }

    public String path() {
        return path;
    }

    public String descripcion() {
        return descripcion;
    }

    public String categoria() {
        return categoria;
    }
}
