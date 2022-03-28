package app.peers.reader.repository;

import app.peers.reader.model.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {
}
