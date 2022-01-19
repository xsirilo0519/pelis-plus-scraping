package co.com.sofka.peliculas.usecase;

import co.com.sofka.peliculas.domain.cartelera.Cartelera;
import co.com.sofka.peliculas.domain.cartelera.command.CrearCarteleraCommand;
import co.com.sofka.peliculas.domain.generic.DomainEvent;

import java.util.List;
import java.util.function.Function;

public class CrearCarteleraUseCase implements Function<CrearCarteleraCommand, List<DomainEvent>> {
    @Override
    public List<DomainEvent> apply(CrearCarteleraCommand command) {
        var cartelera=new Cartelera(command.getCarteleraId(),command.getDate());
        return cartelera.getUncommittedChanges();
    }
}
