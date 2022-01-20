package co.com.sofka.peliculas.infra.handle;

import co.com.sofka.peliculas.domain.cartelera.command.AgregarPeliculaCommand;
import co.com.sofka.peliculas.infra.generic.UseCaseHandle;
import co.com.sofka.peliculas.usecase.AgregarPeliculaUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgregarPeliculaHandle extends UseCaseHandle {
    private final AgregarPeliculaUseCase useCase;


    public AgregarPeliculaHandle(AgregarPeliculaUseCase useCase) {
            this.useCase = useCase;
        }

        @ConsumeEvent(value ="sofkau.pelicula.addPelicula")
        void consumeBlocking(AgregarPeliculaCommand command) {
            var events = useCase.apply(command);
            process(command.getCarteleraId(), events);
        }
    }
