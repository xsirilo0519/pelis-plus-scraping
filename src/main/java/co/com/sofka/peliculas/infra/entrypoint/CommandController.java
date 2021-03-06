package co.com.sofka.peliculas.infra.entrypoint;

import co.com.sofka.peliculas.domain.cartelera.command.AgregarPeliculaCommand;
import co.com.sofka.peliculas.domain.cartelera.command.CrearCarteleraCommand;
import co.com.sofka.peliculas.domain.cartelera.command.ExtraerPeliculasCommand;
import io.vertx.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/peliculas")
public class CommandController {
    private final EventBus bus;

    public CommandController(EventBus bus) {
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/crear")
    public Response executor (CrearCarteleraCommand command){
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addPelicula")
    public Response executor (AgregarPeliculaCommand command){
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/extraerPeliculas")
    public Response executor (ExtraerPeliculasCommand command){
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }


}
