package co.com.sofka.peliculas.infra.handle;

import co.com.sofka.peliculas.domain.cartelera.command.CrearCarteleraCommand;
import co.com.sofka.peliculas.infra.generic.UseCaseHandle;
import co.com.sofka.peliculas.usecase.CrearCarteleraUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CrearCarteleraHandle extends UseCaseHandle {
    private final CrearCarteleraUseCase useCase;


    public CrearCarteleraHandle(CrearCarteleraUseCase useCase) {
        this.useCase = useCase;
    }

    @ConsumeEvent(value = "sofkau.pelicula.crear")
    void consumeBlocking(CrearCarteleraCommand command) {
        var events = useCase.apply(command);
        process(command.getCarteleraId(), events);
    }
}