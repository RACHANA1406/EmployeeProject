package com.example.employee.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.employee.model.Admin;

@Repository
public interface AdminJpaRepository extends JpaRepository<Admin, Integer>{

}