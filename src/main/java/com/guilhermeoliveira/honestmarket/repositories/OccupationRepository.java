package com.guilhermeoliveira.honestmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermeoliveira.honestmarket.entities.Occupation;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Integer>{
}
