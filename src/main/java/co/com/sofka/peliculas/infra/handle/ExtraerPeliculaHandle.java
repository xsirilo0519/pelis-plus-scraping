package co.com.sofka.peliculas.infra.handle;

import co.com.sofka.peliculas.domain.cartelera.command.ExtraerPeliculasCommand;
import co.com.sofka.peliculas.infra.generic.UseCaseHandle;
import co.com.sofka.peliculas.usecase.ExtraerCarteleraUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExtraerPeliculaHandle extends UseCaseHandle {
    private final ExtraerCarteleraUseCase useCase;

    public ExtraerPeliculaHandle(ExtraerCarteleraUseCase useCase) {
            this.useCase = useCase;
        }

        @ConsumeEvent(value ="sofkau.pelicula.extraerPeliculas")
        void consumeBlocking(ExtraerPeliculasCommand command) {
            var events = useCase.apply(command);
            process(command.getCarteleraId(), events);
        }
    }

