package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Termin;

public interface TerminRepository extends JpaRepository<Termin, Integer>{

	List<Termin> findByZauzetost(int i);

}
