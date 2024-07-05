package domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Patient")
@SecondaryTables( {
		@SecondaryTable(name = "Address",
				pkJoinColumns = { @PrimaryKeyJoinColumn(name = "patient_id", referencedColumnName = "id") }
		) })
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(table = "Address")
	private String street;
	@Column(table = "Address")
	private String zip;
	@Column(table = "Address")
	private String city;

	public Patient(String name, String street, String zip, String city) {
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Patient{" +
				"id=" + id +
				", name='" + name + '\'' +
				", street='" + street + '\'' +
				", zip='" + zip + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}
