package s28805.sri04jms.receiver;

import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import s28805.sri04jms.config.JmsConfig;
import s28805.sri04jms.model.HelloMessage;

@Component
public class HelloWorldQueueReceiver1 {
    @JmsListener(destination = JmsConfig.QUEUE_HELLO_WORLD, containerFactory = "queueConnectionFactory")
    public void receiveHelloMessage(@Payload HelloMessage convertedMessage,
                                    @Headers MessageHeaders messageHeaders,
                                    Message message) {
        System.out.println("HelloWorldQueueReceiver1.receiveHelloMessage, message: "+convertedMessage);
    }
}