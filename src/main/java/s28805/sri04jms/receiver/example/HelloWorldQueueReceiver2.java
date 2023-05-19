//package s28805.sri04jms.receiver.example;
//
//import jakarta.jms.Message;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.messaging.handler.annotation.Headers;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//import s28805.sri04jms.config.example.JmsConfig;
//import s28805.sri04jms.model.example.HelloMessage;
//
//@Component
//public class HelloWorldQueueReceiver2 {
//    @JmsListener(destination = JmsConfig.QUEUE_HELLO_WORLD, containerFactory = "queueConnectionFactory")
//    public void receiveHelloMessage(@Payload HelloMessage convertedMessage,
//                                    @Headers MessageHeaders messageHeaders,
//                                    Message message) {
//        System.out.println("HelloWorldQueueReceiver2.receiveHelloMessage, message: "+convertedMessage);
//    }
//}