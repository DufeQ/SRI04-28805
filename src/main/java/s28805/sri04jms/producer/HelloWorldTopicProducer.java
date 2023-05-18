package s28805.sri04jms.producer;


import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import s28805.sri04jms.config.JmsConfig;
import s28805.sri04jms.model.HelloMessage;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class HelloWorldTopicProducer {
    private final JmsTemplate jmsTemplate;
//    @Scheduled(fixedRate = 2500)
    public void sendHello() {
        HelloMessage message = HelloMessage.builder()
                .id(HelloMessage.nextId())
                .createdAt(LocalDateTime.now())
                .message("Hello world!")
                .build();
        jmsTemplate.convertAndSend(JmsConfig.TOPIC_HELLO_WORLD, message);
        System.out.println("HelloWorldTopicProducer.sendHello - sent message: "+message);
    }
}
