package customers.Repository;

import customers.Domain.Product;
import customers.service.ILogger;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO implements IProductDAO {
    private final ILogger logger;

    public ProductDAO(ILogger logger) {
        this.logger = logger;
    }

    public void save(Product product) {
        // Simulate saving the product to the database
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ProductDAO: saving product " + product.getName());
        logger.log("Product is saved in the DB: " + product.getName());
    }
}
