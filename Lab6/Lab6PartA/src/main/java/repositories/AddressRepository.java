package repositories;

import domain.Address;
import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>  {
    //Native Queries

    @Query(value = "SELECT * FROM Address WHERE city = 'Amsterdam'", nativeQuery = true)
    List<Address> findAllInAmsterdam();



}
