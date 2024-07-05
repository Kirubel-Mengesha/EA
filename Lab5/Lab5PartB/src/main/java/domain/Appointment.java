package domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Appointment")

public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String appdate;
	@OneToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name="patient")
	private Patient patient;
	@Embedded
	private Payment payment;
	@OneToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name="doctor")
	private Doctor doctor;

	public Appointment(String appdate, Patient patient, Payment payment, Doctor doctor) {
		this.appdate = appdate;
		this.patient = patient;
		this.payment = payment;
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"id=" + id +
				", appdate='" + appdate + '\'' +
				", patient=" + patient +
				", payment=" + payment +
				", doctor=" + doctor +
				'}';
	}
}
