package kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaApplication implements CommandLineRunner {
	@Autowired
	Sender sender;

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create an account
		AccountInstruction createAccount = new AccountInstruction(12L, "createAccount","Frank Brown");
		ObjectMapper objectMapper = new ObjectMapper();
		String createAccountAsString = objectMapper.writeValueAsString(createAccount);
		System.out.println("Sending a Kafka message:" + createAccountAsString);
		sender.send("accountTopic",createAccountAsString);

		//deposit money
		AccountInstruction deposit = new AccountInstruction(12L, "deposit",20000.0);
		String depositAsString = objectMapper.writeValueAsString(deposit);
		System.out.println("Sending a Kafka message:" + depositAsString);
		sender.send("accountTopic",depositAsString);

		//withdraw money
		AccountInstruction withdraw = new AccountInstruction(12L, "withdraw",4000.0);
		//convert instruction to JSON string
		String withdrawAsString = objectMapper.writeValueAsString(withdraw);
		System.out.println("Sending a Kafka message:" + withdrawAsString);
		sender.send("accountTopic",withdrawAsString);
	}

}
