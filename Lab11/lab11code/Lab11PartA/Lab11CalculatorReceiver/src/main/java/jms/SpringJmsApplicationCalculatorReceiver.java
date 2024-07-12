package jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
public class SpringJmsApplicationCalculatorReceiver {



	public static void main(String[] args)  {
		 SpringApplication.run(SpringJmsApplicationCalculatorReceiver.class, args);
	}



}
