package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String street;
	private String city;
	private String zip;
	private String country;


	public Address(String street, String city, String zip, String country) {
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}
}
