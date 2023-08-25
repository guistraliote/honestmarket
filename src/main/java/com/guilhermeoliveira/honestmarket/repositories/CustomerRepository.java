package com.guilhermeoliveira.honestmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermeoliveira.honestmarket.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
