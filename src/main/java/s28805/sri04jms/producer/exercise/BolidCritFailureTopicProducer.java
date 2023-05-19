package s28805.sri04jms.producer.exercise;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import s28805.sri04jms.config.exercise.BolidJmsConfig;
import s28805.sri04jms.model.exercise.BolidCommunication;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class BolidCritFailureTopicProducer {
    private final JmsTemplate jmsTemplate;
//    @Scheduled(fixedRate = 15000)
    public void sendBolidInfo() {
        BolidCommunication message = BolidCommunication.builder()
                .id(BolidCommunication.nextId())
                .createdAt(LocalDateTime.now())
                .message("-----Critical Error")
                .build();
        jmsTemplate.convertAndSend(BolidJmsConfig.TOPIC_CRIT_FAILURE, message);
//        System.out.println("BolidInfoTopicProducer.sendBolidInfo - sent message: "+message);
    }
}
