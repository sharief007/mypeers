package app.peers.router.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collation = "ChatMessage")
public record ChatMessage(
        @Id
        String messageId,
        String senderId,
        String ReceiverId,
        String roomId,
        String messageBody,
        String messageType,
        String MediaId,
        Instant timestamp
){}
