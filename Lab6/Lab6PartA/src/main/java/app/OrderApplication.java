package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.*;
import specifications.*;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class OrderApplication implements CommandLineRunner {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CDRepository cdRepository;

	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create and save initial data
		Product cdProduct = new CD("The Best of 1980-1990", 9.99, "U2");
		Product dvdProduct = new DVD("Rocky3", 15.99, "Action");
		Product bookProduct = new Book("Java Programming", 45.00, 9780135166307L);

		OrderLine ol1 = new OrderLine(2, cdProduct);
		OrderLine ol2 = new OrderLine(1, dvdProduct);
		OrderLine ol3 = new OrderLine(1, bookProduct);

		Order order1 = new Order("12345", "2024-06-30", "closed");
		order1.addOrderLine(ol1);
		order1.addOrderLine(ol2);

		Order order2 = new Order("12346", "2024-06-30", "open");
		order2.addOrderLine(ol3);

		Address address1 = new Address("100 Main St", "Amsterdam", "12345", "Netherlands");
		Address address2 = new Address("456 Elm St", "FairField", "52557", "USA");
		Address address3 = new Address("789 Oak St", "FairField", "2389HJ", "USA");

		Customer customer1 = new Customer("John", "Doe", address1);
		Customer customer2 = new Customer("Jane", "Smith", address2);
		Customer customer3 = new Customer("Bob", "Johnson", address3);

		customer1.addOrder(order1);
		customer2.addOrder(order2);

		order1.setCustomer(customer1);
		order2.setCustomer(customer2);

		customerRepository.save(customer1);
		customerRepository.save(customer2);
		customerRepository.save(customer3);

		// Print the orders to verify setup
		printOrder(order1);
		printOrder(order2);

		// Execute and print the sample queries
		executeSampleQueries();
	}

	private void executeSampleQueries() {
		System.out.println("\n--- Sample Queries ---");

		// All customers
		System.out.println("All customers:");
		customerRepository.findAll().forEach(customer -> System.out.println(customer.toString()));

		// All CDs from U2 with a price smaller than 10 euro
		System.out.println("\nAll CDs from U2 with a price smaller than 10 euro:");
		cdRepository.findByArtistAndPriceLessThan("U2", 10.0).forEach(cd -> System.out.println(cd.toString()));

		// All customers with zip code 2389HJ
		System.out.println("\nAll customers with zip code 2389HJ:");
		customerRepository.findByAddressZip("2389HJ").forEach(customer -> System.out.println(customer.toString()));

		// All customers who ordered a DVD with the name Rocky3
		System.out.println("\nAll customers who ordered a DVD with the name Rocky3:");
		customerRepository.findByTheOrdersOrderLinesProductName("Rocky3").forEach(customer -> System.out.println(customer.toString()));

		// All customers from the USA
		System.out.println("\nAll customers from the USA:");
		customerRepository.findAllFromUSA().forEach(customer -> System.out.println(customer.toString()));

		// All CDs from U2
		System.out.println("\nAll CDs from U2:");
		cdRepository.findAllByArtist("U2").forEach(cd -> System.out.println(cd.toString()));

		// Order numbers of all orders with status 'closed'
		System.out.println("\nOrder numbers of all orders with status 'closed':");
		orderRepository.findOrderNumbersByStatusClosed().forEach(orderNum -> System.out.println(orderNum));

		// First and last names of all customers who live in Amsterdam
		System.out.println("\nFirst and last names of all customers who live in Amsterdam:");
		orderRepository.findCustomerNamesByCityAmsterdam().forEach(record -> {
			System.out.println(record[0] + " " + record[1]);
		});

		// Order numbers of all orders from customers who live in FairField
		System.out.println("\nOrder numbers of all orders from customers who live in FairField:");
		orderRepository.findOrderNumbersByCustomerCity("FairField").forEach(orderNum -> System.out.println(orderNum));

		// All CDs from U2 with a price greater than 15 euro
		System.out.println("\nAll CDs from U2 with a price greater than 15 euro:");
		cdRepository.findByArtistAndPriceGreaterThan("U2", 15.0).forEach(cd -> System.out.println(cd.toString()));

		// All addresses in Amsterdam
		System.out.println("\nAll addresses in Amsterdam:");
		addressRepository.findAllInAmsterdam().forEach(address -> System.out.println(address.toString()));

		// Specifications-based queries
		System.out.println("\n--- Specifications-based Queries ---");

		// Order numbers of all orders with status 'closed' (using specifications)
		System.out.println("Order numbers of all orders with status 'closed' (using specifications):");
		orderRepository.findAll(OrderSpecifications.hasStatusClosed()).forEach(order -> System.out.println(order.getOrderNum()));

		// All customers who live in Amsterdam (using specifications)
		System.out.println("\nAll customers who live in Amsterdam (using specifications):");
		customerRepository.findAll(CustomerSpecifications.livesInCity("Amsterdam")).forEach(customer -> System.out.println(customer.toString()));

		// All CDs from U2 with a price greater than 15 euro (using specifications)
		System.out.println("\nAll CDs from U2 with a price greater than 15 euro (using specifications):");
		cdRepository.findAll(CDSpecifications.artistAndPriceGreaterThan("U2", 15.0)).forEach(cd -> System.out.println(cd.toString()));
	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrderNum());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstname() + " " + cust.getLastname());
		for (OrderLine orderline : order.getOrderLines()) {
			System.out.println("Order line: quantity= " + orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " Price= " + product.getPrice());
		}
	}
}
