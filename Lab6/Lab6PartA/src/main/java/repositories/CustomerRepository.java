package repositories;


import domain.CD;
import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    //method name queries
    List<Customer> findAll();

    List<Customer> findByAddressZip(String zip);

    List<Customer> findByTheOrdersOrderLinesProductName(String name);

    //named queries

    @Query(name = "Customer.findAllFromUSA")
    List<Customer> findAllFromUSA();




}
