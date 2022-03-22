package app.peers.router.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class MessagingController {

    private final Logger logger;

    MessagingController() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @MessageMapping("/app/{dest}")
    public void print(@DestinationVariable(value = "dest") String dest,
                      @Payload String payload){
       logger.info(String.format("%s : %s", dest,payload));
    }
}