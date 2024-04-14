package com.example.employee.model;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer ticketId;
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;
	@Column(name="date")
	private LocalDate date;
	@Column(name="type")
	private String type;
	@Column(name="issue")
	private String issue;
	@Column(name="status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="adminid")
	@JsonIgnoreProperties("tickets")
	private Admin admin;
	
	
	public Ticket() {}

	public Ticket(Integer ticketId, Employee employee, LocalDate date, String type, String issue, String status, Admin admin) {
		this.ticketId = ticketId;
		this.employee = employee;
		this.date=date;
		this.type=type;
		this.issue = issue;
		this.status = status;
		this.admin=admin;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
