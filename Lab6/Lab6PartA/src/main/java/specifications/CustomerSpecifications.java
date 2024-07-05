package specifications;

import domain.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecifications {
    public static Specification<Customer> livesInCity(String city) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("address").get("city"), city);
    }
}
