package com.example.employee.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name="admin")
public class Admin {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer adminId;
	@Column(name="typeofticket")
	private String typeOfTicket;
	@OneToMany(mappedBy="admin")
	@JsonIgnoreProperties("admin")
	private List<Ticket> tickets=new ArrayList<>();
	
	public Admin(){}

	public Admin(Integer adminId, String typeOfTicket, List<Ticket> tickets) {
		this.adminId = adminId;
		this.tickets = tickets;
		this.typeOfTicket=typeOfTicket;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	public String getTypeOfTicket() {
		return typeOfTicket;
	}

	public void setTypeOfTicket(String typeOfTicket) {
		this.typeOfTicket = typeOfTicket;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
