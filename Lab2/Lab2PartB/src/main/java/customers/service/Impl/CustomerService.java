package customers.service.Impl;

import customers.Domain.Address;
import customers.Domain.Customer;
import customers.Repository.ICustomerDAO;
import customers.service.ICustomerService;
import customers.service.IEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
//	ICustomerDAO customerDAO = new CustomerDAO();
//	IEmailSender emailSender = new EmailSender();
	private ICustomerDAO customerDAO;
	private IEmailSender emailSender;

	// Setter for customerDAO
	@Autowired
	public void setCustomerDAO(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	// Setter for emailSender
	@Autowired
	public void setEmailSender(IEmailSender emailSender) {
		this.emailSender = emailSender;
	}


	public void addCustomer(String name, String email, String street,
			String city, String zip) {
		Customer customer = new Customer(name, email);
		Address address = new Address(street, city, zip);
		customer.setAddress(address);
		customerDAO.save(customer);
		emailSender.sendEmail(email, "Welcome " + name + " as a new customer");
	}
}
