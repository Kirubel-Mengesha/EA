package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender2 {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topicA2, String message){
        kafkaTemplate.send(topicA2, message);
    }
}
