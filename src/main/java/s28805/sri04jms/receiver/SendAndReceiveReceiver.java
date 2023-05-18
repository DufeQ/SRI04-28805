package s28805.sri04jms.receiver;

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
import s28805.sri04jms.config.JmsConfig;
import s28805.sri04jms.model.HelloMessage;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SendAndReceiveReceiver {
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.QUEUE_SEND_AND_RECEIVE) public
    void receiveAndRespond(@Payload HelloMessage convertedMessage,
                           @Headers MessageHeaders headers,
                           Message message) throws JMSException {
        System.out.println("SendAndReceiveReceiver.receiveAndRespond message: "+convertedMessage);
                Destination replyTo = message.getJMSReplyTo();
        HelloMessage msg = HelloMessage.builder()
                .id(HelloMessage.nextId())
                .createdAt(LocalDateTime.now())
                .message("You're welcome!")
                .build();
        jmsTemplate.convertAndSend(replyTo, msg);
    }
}