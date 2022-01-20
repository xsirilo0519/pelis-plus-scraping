package co.com.sofka.peliculas.usecase;

import co.com.sofka.peliculas.domain.cartelera.Cartelera;
import co.com.sofka.peliculas.domain.cartelera.command.AgregarPeliculaCommand;
import co.com.sofka.peliculas.domain.generic.DomainEvent;
import co.com.sofka.peliculas.domain.generic.EventStoreRepository;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class AgregarPeliculaUseCase implements Function<AgregarPeliculaCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;

    public AgregarPeliculaUseCase(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AgregarPeliculaCommand command) {
        var events=repository.getEventsBy("cartelera",command.getCarteleraId());
        var cartelera= Cartelera.from(command.getCarteleraId(),events);
        cartelera.addPelicula(command.getPeliculaId(),command.getNombre(),command.getPath(),command.getDescripcion(),command.getCategoria());
        return cartelera.getUncommittedChanges();
    }
}
