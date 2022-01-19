package co.com.sofka.peliculas.infra.entrypoint;

import co.com.sofka.peliculas.domain.cartelera.command.AgregarPeliculaCommand;
import co.com.sofka.peliculas.domain.cartelera.command.CrearCarteleraCommand;
import io.vertx.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
        System.out.println(command.getCarteleraId()+" "+command.getPeliculaId()+" "+command.getNombre()+" "+command.getPath()+" "+command.getDescripcion()+" "+command.getCategoria());
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }


}
