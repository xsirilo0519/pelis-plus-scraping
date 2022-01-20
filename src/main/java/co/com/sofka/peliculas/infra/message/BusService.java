package co.com.sofka.peliculas.infra.message;

import co.com.sofka.peliculas.domain.generic.DomainEvent;
import co.com.sofka.peliculas.infra.entrypoint.SocketService;
import co.com.sofka.peliculas.infra.generic.EventSerializer;

import com.rabbitmq.client.*;
import io.quarkiverse.rabbitmqclient.RabbitMQClient;
import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class BusService {
    private static final String EXCHANGE = "executor";
    private static final String EVENT_QUEUE = "event.queue";

    private final EventBus bus;
    private final RabbitMQClient rabbitMQClient;
    private final SocketService socket;

    private Channel channel;

    public BusService(EventBus bus, SocketService socket, RabbitMQClient rabbitMQClient) {
        this.bus = bus;
        this.socket = socket;
        this.rabbitMQClient = rabbitMQClient;
    }
    public void onApplicationStart(@Observes StartupEvent event) throws IOException {
        Connection connection = rabbitMQClient.connect();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.TOPIC, true);

        //for event
        channel.queueDeclare(EVENT_QUEUE, true, false, false, null);
        //"trigger-event" es el nombre del bind
        channel.queueBind(EVENT_QUEUE, EXCHANGE, "trigger-event");

        channel.basicConsume(EVENT_QUEUE, true, setupReceivingForEvent());
    }

    private Consumer setupReceivingForEvent() {
        return new DefaultConsumer(channel) {
            //el cuerpo viaja en bytes
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //convierto los bytes en string
                var message = new String(body, StandardCharsets.UTF_8);
                try {
                    //abstracto de debe porbner getcontentType
                    var event = EventSerializer.instance()
                            .deserialize(message, Class.forName(properties.getContentType()));
                    //este esta publicando los eventos para esperar quien lo va a escuchar
                    bus.publish(event.getType(), event);
                    //este permite comunicarme desde el backend al frontend
                    socket.send(event.getCorrelationId(), event);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    public void send(DomainEvent event) {
        try {
            var message = EventSerializer.instance().serialize(event);
            var props = new AMQP.BasicProperties.Builder().contentType(event.getClass().getTypeName()).build();
            channel.basicPublish(EXCHANGE, "trigger-event", props, message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

