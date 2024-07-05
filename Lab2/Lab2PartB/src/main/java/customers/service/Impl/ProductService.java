package customers.service.Impl;

import customers.Domain.Product;
import customers.Repository.ProductDAO;
import customers.service.IEmailSender;
import customers.service.ILogger;
import customers.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    private final ILogger logger;
    private final IEmailSender emailSender;
    private final ProductDAO productDAO;

    @Autowired
    public ProductService(ILogger logger, IEmailSender emailSender, ProductDAO productDAO) {
        this.logger = logger;
        this.emailSender = emailSender;
        this.productDAO = productDAO;
    }

    public void addProduct(String name, String description) {
        Product product = new Product(name, description);
        productDAO.save(product);
        emailSender.sendEmail("apple@company.com", "New product added: " + name);
        logger.log("Product added: " + name);
    }
}
