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
@Table(name= "order_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String orderNum;
	private String date;
	private String status;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Customer customer;
	@OneToMany (cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Collection<OrderLine> orderLines = new ArrayList<OrderLine>();


	public Order(String orderNum, String date, String status) {
		this.orderNum = orderNum;
		this.date = date;
		this.status = status;
	}



	public boolean addOrderLine(OrderLine ol) {
		return orderLines.add(ol);
	}


}
