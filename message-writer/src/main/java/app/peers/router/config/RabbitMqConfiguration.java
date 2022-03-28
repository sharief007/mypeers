package app.peers.router.config;

import app.peers.router.model.ChatMessage;
import app.peers.router.repository.ChatMessageRepository;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Configuration
public class RabbitMqConfiguration {

    private final ChatMessageRepository repository;

    public RabbitMqConfiguration(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "chat.message.save", durable = "true"))
    public void processMessage(Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        try {
//            channel.basicAck(deliveryTag, false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ChatMessage chatMessage = convertFrom(message);
        repository.insert(chatMessage)
                .subscribe( msg -> {
                    try {
                        channel.basicAck(deliveryTag, false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private ChatMessage convertFrom(Message message) {
        MessageProperties properties = message.getMessageProperties();
        Map<String, Object> headers = properties.getHeaders();

        String content = new String(message.getBody());
        return new ChatMessage(properties.getMessageId(),
                headers.get("SENDERID").toString(),
                headers.get("RECEIVERID").toString(),
                headers.get("ROOMID").toString(),
                content,
                properties.getContentType(),
                headers.get("MEDIAID").toString(),
                Instant.now()
                //properties.getTimestamp().toInstant()
        );
    }

}
