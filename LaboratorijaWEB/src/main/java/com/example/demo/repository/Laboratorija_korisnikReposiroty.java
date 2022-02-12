package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Laboratorija_korisnik;
import model.Uloga;

public interface Laboratorija_korisnikReposiroty extends JpaRepository<Laboratorija_korisnik, Integer>{
//	@Query("select k from Laboratorija_korisnik k where k.idKorisnik=:unetIDKorisnika and k.brLicneKarte=:unetiBrLicneKarteKorisnika and k.Uloga.naziv like 'klijent' ")
//	public Laboratorija_korisnik logovanKlijent(@Param("unetIDKorisnika")Integer unetIDKorisnika, @Param("unetiBrLicneKarteKorisnika")String unetiBrLicneKarteKorisnika);
//	
//	@Query("select l from Laboratorija_korisnik l where l.idKorisnik=:unetIDKorisnika and l.brLicneKarte=:unetiBrLicneKarteKorisnika and l.Uloga.naziv like 'laborant' ")
//	public Laboratorija_korisnik logovanAdmin(@Param("unetIDKorisnika")Integer unetIDKorisnika, @Param("unetiBrLicneKarteKorisnika")String unetiBrLicneKarteKorisnika);
//
//	@Query("select k from Laboratorija_korisnik k where k.brLicneKarte like 'brLicneKarteKlijent' ")
//	public Laboratorija_korisnik findByBrLicneKarte(@Param("brLicneKarteKlijent")String brLicneKarteKlijent);
//	
	public   Optional<Laboratorija_korisnik> findBybrLicneKarte(String brLicneKarte);
	
	public List<Laboratorija_korisnik> findByUloga(Uloga u);
	
//	@Query("select l from Laboratorija_korisnik l where l.Uloga.naziv like 'laborant' ")
//	public List<Laboratorija_korisnik> findByUloga();
}