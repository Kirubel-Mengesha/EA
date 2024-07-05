package specifications;

import domain.CD;
import org.springframework.data.jpa.domain.Specification;

public class CDSpecifications {
    public static Specification<CD> artistAndPriceGreaterThan(String artist, double amount) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("artist"), artist),
                criteriaBuilder.greaterThan(root.get("price"), amount)
        );
    }
}