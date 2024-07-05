package customers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ICustomerService customerService = context.getBean(ICustomerService.class);

		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");

		ProductService productService = context.getBean(ProductService.class);
		productService.addProduct("Iphone 15", " A 6.1-inch Super Retina XDR display, A17 Bionic chip, advanced 48MP camera system, 5G connectivity, enhanced Face ID, longer battery life, MagSafe compatibility, and comes in multiple colors and storage options.");
	}
}

