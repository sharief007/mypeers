package app.peers.reader.controller;

import app.peers.reader.model.ChatMessage;
import app.peers.reader.repository.ChatMessageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MessageController {

    private final ChatMessageRepository repository;

    public MessageController(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("messages")
    public Flux<ChatMessage> getMessages() {
        return repository.findAll();
    }
}
