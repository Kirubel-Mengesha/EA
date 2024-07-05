package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.*;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner{
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderLineRepository orderLineRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private AddressRepository addressRepository;


	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product();
		product.setName("Iphone 15");
		product.setPrice(35.50);


		Product product2 = new Product();
		product2.setName("Samsung Galaxy Ultra");
		product2.setPrice(12.98);


		OrderLine ol1 = new OrderLine(2, product);
		OrderLine ol2 = new OrderLine(4, product2);

		Order o1 = new Order("234743", "12/10/06", "open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);

		Address address = new Address("1000 N 4th St", "FairField", "52557", "USA");

		Customer c1 = new Customer("Frank", "Brown",address);
		c1.addOrder(o1);
		o1.setCustomer(c1);

		orderRepository.save(o1);


		printOrder(o1);
	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrderNum());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstname() + " "
				+ cust.getLastname());
		for (OrderLine orderline : order.getOrderLines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " " + "Price= " + product.getPrice());
		}

	}
}
