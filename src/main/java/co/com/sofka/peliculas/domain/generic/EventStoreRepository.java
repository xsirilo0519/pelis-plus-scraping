package co.com.sofka.peliculas.domain.generic;

import co.com.sofka.peliculas.infra.generic.StoredEvent; //TODO: Regla de la dependencia, se soluciona con un ioc pero no se como

import java.util.List;



public interface EventStoreRepository {

    List<DomainEvent> getEventsBy(String aggregateName, String aggregateRootId);


    void saveEvent(String aggregateName, String aggregateRootId, StoredEvent storedEvent);

}