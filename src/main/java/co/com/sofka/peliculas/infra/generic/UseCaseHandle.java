package co.com.sofka.peliculas.infra.generic;

import co.com.sofka.peliculas.domain.generic.DomainEvent;
import co.com.sofka.peliculas.domain.generic.EventStoreRepository;
import co.com.sofka.peliculas.domain.generic.StoredEvent;
import co.com.sofka.peliculas.infra.message.BusService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
//ejecuta casos de uso de manera generica

public abstract class UseCaseHandle {
    @Inject
    private  EventStoreRepository repository;

    @Inject
    private BusService busService;;

    public void process(String programId, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("cartelera", programId, storedEvent));

        events.forEach(busService::send);
    }
}