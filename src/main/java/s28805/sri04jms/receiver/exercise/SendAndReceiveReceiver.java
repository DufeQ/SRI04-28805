package s28805.sri04jms.receiver.exercise;
//package s28805.sri04jms.receiver.example;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import s28805.sri04jms.config.exercise.BolidJmsConfig;
import s28805.sri04jms.model.exercise.BolidCommunication;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SendAndReceiveReceiver {
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = BolidJmsConfig.BOLID_SEND_AND_RECEIVE) public
    void receiveAndRespond(@Payload BolidCommunication convertedMessage,
                           @Headers MessageHeaders headers,
                           Message message) throws JMSException {
//        System.out.println("SendAndReceiveReceiver.receiveAndRespond message: "+convertedMessage);
        Destination replyTo = message.getJMSReplyTo();
        double a = Math.random();
        BolidCommunication msg = BolidCommunication.builder()
                .id(BolidCommunication.nextId())
                .createdAt(LocalDateTime.now())
                .message("Approved")
                .build();
        if (a > 0.5){
            msg = BolidCommunication.builder()
                    .id(BolidCommunication.nextId())
                    .createdAt(LocalDateTime.now())
                    .message("Denied")
                    .build();
        }


        jmsTemplate.convertAndSend(replyTo, msg);
    }
}