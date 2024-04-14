package com.example.employee.repository;
import java.time.LocalDate;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.employee.model.*;

@Repository
public interface TicketJpaRepository extends JpaRepository<Ticket, Integer>{
	List<Ticket> findByEmployee(Employee employee);
	List<Ticket> findByDateBetween(LocalDate fromDate, LocalDate toDate);
}
