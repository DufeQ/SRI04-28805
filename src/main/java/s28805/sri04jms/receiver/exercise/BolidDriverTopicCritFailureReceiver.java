package s28805.sri04jms.receiver.exercise;

import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import s28805.sri04jms.config.exercise.BolidJmsConfig;
import s28805.sri04jms.model.exercise.BolidCommunication;
import s28805.sri04jms.model.exercise.BolidInfo;

@Component public class BolidDriverTopicCritFailureReceiver {
    @JmsListener(destination = BolidJmsConfig.TOPIC_CRIT_FAILURE, containerFactory = "bolidTopicConnectionFactory")
    public void receiveHelloMessage(@Payload BolidCommunication convertedMessage,
                                    @Headers MessageHeaders messageHeaders,
                                    Message message) {
        System.out.println("Driver, message: "+convertedMessage);
    }
}