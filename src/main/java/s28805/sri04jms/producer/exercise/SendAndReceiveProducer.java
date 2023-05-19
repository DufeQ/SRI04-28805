package s28805.sri04jms.producer.exercise;
//package s28805.sri04jms.producer.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import s28805.sri04jms.config.exercise.BolidJmsConfig;
import s28805.sri04jms.model.exercise.BolidCommunication;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SendAndReceiveProducer {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    @Scheduled(fixedRate = 15000)
    public void sendAndReceive() throws JMSException, JsonProcessingException {
        BolidCommunication message = BolidCommunication.builder()
                .id(BolidCommunication.nextId())
                .createdAt(LocalDateTime.now())
                .message("Premission for the break in the pit")
                .build();
        TextMessage responseMessage = (TextMessage) jmsTemplate.sendAndReceive(
                BolidJmsConfig.BOLID_SEND_AND_RECEIVE, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage plainMessage = session.createTextMessage(); try {
                            plainMessage.setText(objectMapper.writeValueAsString(message));
                            plainMessage.setStringProperty("_type",
                                    BolidCommunication.class.getName()); return
                                    plainMessage;
                        } catch (JsonProcessingException e) { throw new
                                JMSException("conversion to json failed: " + e.getMessage());
                        }
                    }
                });
        String responseText = responseMessage.getText();
        BolidCommunication responseConverted = objectMapper.readValue(responseText,
                BolidCommunication.class);
        System.out.println("SendAndReceiveProducer.sendAndReceive got response: " +responseText+"\n\tconvertedMessage: "+responseConverted);
    }
}
