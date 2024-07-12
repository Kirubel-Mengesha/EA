package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;


@SpringBootApplication
@EnableJms
public class SpringJmsCalculatorSenderApplication implements CommandLineRunner {
	@Autowired
	JmsTemplate jmsTemplate;


	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJmsCalculatorSenderApplication.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		Calculator calc = new Calculator(2, "+");
		//convert person to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String calcAsString = objectMapper.writeValueAsString(calc);

		System.out.println("Sending a JMS message:" + calcAsString);
		jmsTemplate.convertAndSend("testQueue",calcAsString);


	}

}
