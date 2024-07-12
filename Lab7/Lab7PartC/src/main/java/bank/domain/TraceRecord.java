package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class TraceRecord {
	@Id
	@GeneratedValue
	private long id;
	private Date date;
	private String traceName;
	public TraceRecord() {
	}
	public TraceRecord(Date date, String traceName) {
		this.date = date;
		this.traceName = traceName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return traceName;
	}
	public void setName(String name) {
		this.traceName = name;
	}
	
	
	
}
