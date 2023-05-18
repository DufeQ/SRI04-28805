package s28805.sri04jms.producer.exercise;


import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import s28805.sri04jms.config.example.JmsConfig;
import s28805.sri04jms.config.exercise.BolidJmsConfig;
import s28805.sri04jms.model.exercise.BolidInfo;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BolidInfoTopicProducer {
    private final JmsTemplate jmsTemplate;
    @Scheduled(fixedRate = 15000)
    public void sendBolidInfo() {
        BolidInfo message = BolidInfo.builder()
                .id(BolidInfo.nextId())
                .createdAt(LocalDateTime.now())
                .engineTemp(Double.toString( 100))
                .tirePressFront(Double.toString( 22.5))
                .tirePressEnd(Double.toString(19.5))
                .build();
        jmsTemplate.convertAndSend(BolidJmsConfig.TOPIC_BOLID_WORLD, message);
//        System.out.println("BolidInfoTopicProducer.sendBolidInfo - sent message: "+message);
    }
}
