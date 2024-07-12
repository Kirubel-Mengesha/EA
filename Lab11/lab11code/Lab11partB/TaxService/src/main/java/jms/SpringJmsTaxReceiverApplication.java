package jms;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
public class SpringJmsTaxReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJmsTaxReceiverApplication.class, args);
	}



}


