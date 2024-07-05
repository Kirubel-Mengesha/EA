package domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "order_Line")
public class OrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int quantity;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Product product;



	public OrderLine(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}


}
