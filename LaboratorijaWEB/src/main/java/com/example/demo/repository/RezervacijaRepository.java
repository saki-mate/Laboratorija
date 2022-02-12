package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {

	@Query("select r from Rezervacija r where r.laboratorijaKorisnik.idKorisnik=:korisnik")
	public List<Rezervacija> findByidKorisnik(@Param("korisnik") Integer korisnik);

	@Query("select r from Rezervacija r where r.laboratorijaKorisnik.idKorisnik=:korisnik and r.idLaborant=:laborant")
	public List<Rezervacija> findByidKorisnikAndLaborant(@Param("korisnik") Integer korisnik, @Param("laborant") Integer laborant);
}
