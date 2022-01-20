package co.com.sofka.peliculas.infra.materialize;
import co.com.sofka.peliculas.domain.cartelera.event.CarteleraCreada;
import co.com.sofka.peliculas.domain.cartelera.event.PeliculaAgregada;
import co.com.sofka.peliculas.domain.cartelera.event.PeliculasExtraidas;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CarteleraHandle {

    private final MongoClient mongoClient;

    public CarteleraHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    //ojo el value se debe llamar igual que en el courseCreted que es un event
    @ConsumeEvent(value = "sofka.campus.cartelera.carteleracreada", blocking = true)
    void consumeProgramCreated(CarteleraCreada event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("date", event.getDate());

        mongoClient.getDatabase("queriesPeli")
                .getCollection("cartelera")
                .insertOne(new Document(document));
    }

    //tienes que ser el mismo del evento
    @ConsumeEvent(value = "sofka.campus.cartelera.peliculaagregada", blocking = true)
    void consumePeliculaAgregada(PeliculaAgregada event) {
        BasicDBObject document = new BasicDBObject();
        document.put("pelicula."+event.getPeliculaId()+".nombre",event.getNombre());
        document.put("pelicula."+event.getPeliculaId()+".categoria",event.getCategoria());
        document.put("pelicula."+event.getPeliculaId()+".descripcion",event.getDescripcion());
        document.put("pelicula."+event.getPeliculaId()+".path", event.getPath());
        document.put("pelicula."+event.getPeliculaId()+".fecha", Instant.now());
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queriesPeli")
                .getCollection("cartelera")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }

    @ConsumeEvent(value = "sofka.campus.cartelera.peliculasextraidas", blocking = true)
    void consumePeliculaExtraida(PeliculasExtraidas event) {
        System.out.println(event.getNombre()+" "+event.getPeliculaId());
        BasicDBObject document = new BasicDBObject();
        document.put("pelicula."+event.getPeliculaId()+".nombre",event.getNombre());
        document.put("pelicula."+event.getPeliculaId()+".categoria",event.getCategoria());
        document.put("pelicula."+event.getPeliculaId()+".descripcion",event.getDescripcion());
        document.put("pelicula."+event.getPeliculaId()+".path", event.getPath());
        document.put("pelicula."+event.getPeliculaId()+".fecha", Instant.now());
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queriesPeli")
                .getCollection("cartelera")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }

}