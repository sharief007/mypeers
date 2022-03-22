package app.peers.router.repository;

import app.peers.router.model.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {
}
