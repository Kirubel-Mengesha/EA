package specifications;

import domain.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecifications {
    public static Specification<Order> hasStatusClosed() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), "closed");
    }
}
