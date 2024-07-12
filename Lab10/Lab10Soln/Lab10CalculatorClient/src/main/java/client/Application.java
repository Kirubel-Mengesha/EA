package client;

import generated.AddResponse;
import generated.SubtractResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import generated.Person;

import java.util.Optional;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	GreetingClient greetingClient;

	@Autowired
	private CalculatorClient calculatorClient;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Person person = new Person();
//		person.setFirstName("Frank");
//		person.setLastName("Brown");
//		System.out.println(greetingClient.getMessage(person));

		int num1 = 3;
		int num2 = 66;

		AddResponse addResponse1 = calculatorClient.add(num1, num2);
		System.out.printf("%d + %d = %d%n", num1, num2, addResponse1.getResult());


		int num3 = 22;
		int num4 = 45;

		AddResponse addResponse2 = calculatorClient.add(num3, num4);
		System.out.printf("%d + %d = %d%n", num3, num4, addResponse2.getResult());


		int num5 = 5;
		int num6 = 2;

		SubtractResponse subtractResponse = calculatorClient.subtract(num5, num6);
		System.out.printf("%d - %d = %d%n", num5, num6, subtractResponse.getResult());
	}



}


