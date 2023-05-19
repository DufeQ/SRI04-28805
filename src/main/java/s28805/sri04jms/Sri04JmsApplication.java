package s28805.sri04jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import s28805.sri04jms.receiver.exercise.BolidInfoTopicReceiver2;

@SpringBootApplication
public class Sri04JmsApplication {

    public static void main(String[] args) {
        //SpringApplication.run(Sri04JmsApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(Sri04JmsApplication.class, args);
        BolidInfoTopicReceiver2 bolidInfoTopicReceiver2 = new BolidInfoTopicReceiver2(context);
    }

}
