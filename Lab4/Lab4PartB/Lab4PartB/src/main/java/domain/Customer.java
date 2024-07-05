package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String firstname;
	private String lastname;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Address address;
	@OneToMany (cascade={CascadeType.PERSIST},  mappedBy="customer")
	private Collection<Order> theOrders = new ArrayList<Order>();

	public Customer(String firstname, String lastname, Address address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	public void addOrder(Order order){
		theOrders.add(order);
	}


}
