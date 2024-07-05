package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "customers")
public class AppConfig {

    @Bean
    public ILogger logger() {
        return new Logger();
    }

    @Bean
    public ICustomerDAO customerDAO() {
        return new CustomerDAO(logger());
    }

    @Bean
    public IEmailSender emailSender() {
        return new EmailSender(logger());
    }

    @Bean
    public ICustomerService customerService() {
        CustomerService customerService = new CustomerService();
        customerService.setCustomerDAO(customerDAO());
        customerService.setEmailSender(emailSender());
        return customerService;
    }
}

