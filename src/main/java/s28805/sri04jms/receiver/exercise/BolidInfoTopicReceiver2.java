package s28805.sri04jms.receiver.exercise;

import jakarta.jms.Message;
import lombok.AllArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import s28805.sri04jms.config.exercise.BolidJmsConfig;
import s28805.sri04jms.model.exercise.BolidCommunication;
import s28805.sri04jms.model.exercise.BolidInfo;
import s28805.sri04jms.producer.exercise.BolidCritFailureTopicProducer;
import s28805.sri04jms.producer.exercise.BolidFailureTopicProducer;

@Component
@AllArgsConstructor
public class BolidInfoTopicReceiver2 {

    public ConfigurableApplicationContext context;
    @JmsListener(destination = BolidJmsConfig.TOPIC_BOLID, containerFactory = "bolidTopicConnectionFactory")
    public void receiveHelloMessage(@Payload BolidInfo convertedMessage,
                                    @Headers MessageHeaders messageHeaders,
                                    Message message) {
        BolidFailureTopicProducer bolidFailureTopicProducer = new BolidFailureTopicProducer(context.getBean(JmsTemplate.class));
        BolidCritFailureTopicProducer bolidCritFailureTopicProducer = new BolidCritFailureTopicProducer(context.getBean(JmsTemplate.class));

        if (Double.parseDouble(convertedMessage.getEngineTemp()) > 149d){ //error
            bolidCritFailureTopicProducer.sendBolidInfo();
        }
        if (Double.parseDouble(convertedMessage.getTirePressFront()) < 20d){ //fatalError
            bolidFailureTopicProducer.sendBolidInfo();
        }
    }
}