package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repository.Laboratorija_korisnikReposiroty;
import com.example.demo.repository.RezervacijaRepository;
import com.example.demo.repository.TerminRepository;
import com.example.demo.repository.UlogaRepository;

import model.Laboratorija_korisnik;
import model.Uloga;

import com.example.demo.repository.AnalizaReposirtory;

@Controller
@RequestMapping(value = "controller")
public class LaboratorijaController { 
	@Autowired
	UlogaRepository ur;
	
	@Autowired
	Laboratorija_korisnikReposiroty lkr;

	@Autowired
	RezervacijaRepository rr;
	
	@Autowired
	TerminRepository tr;
	
	@Autowired
	AnalizaReposirtory ar;

	@RequestMapping(value = "/loginUser")
	public String login() {
		return "login";
	}

	 @RequestMapping(value = "dodajUBazu", method = RequestMethod.POST)
		public String saveUser(HttpServletRequest request, String brLicneKarte, String sifra, String ime, String prezime, String datumRodjenja, String kontakt, Integer cbUloga) {
		 	Laboratorija_korisnik u=new Laboratorija_korisnik();
		 	System.out.println("pre role");
		 	
		 	u.setBrLicneKarte(brLicneKarte);
			u.setSifra(sifra);
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    u.setSifra(passwordEncoder.encode(u.getSifra()));
			
		    u.setKontakt(kontakt);
		    u.setPrezime(prezime);
		    u.setIme(ime);
		    u.setDatumRodjenja(datumRodjenja);
		    
		    Uloga role = ur.findById(cbUloga).get();
		    
			u.setUloga(role);
			role.addLaboratorijaKorisnik(u);
		    
		    lkr.save(u);

		    return "/index";
		}


	 @RequestMapping(value = "dodajUBazuKlijent", method = RequestMethod.POST)
		public String saveKlijent(String brLicneKarte, String sifra, String ime, String prezime, String datumRodjenja, String kontakt) {
		 	Laboratorija_korisnik u=new Laboratorija_korisnik();
		 	
		 	u.setBrLicneKarte(brLicneKarte);
			u.setSifra(sifra);
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    u.setSifra(passwordEncoder.encode(u.getSifra()));
			
		    u.setKontakt(kontakt);
		    u.setPrezime(prezime);
		    u.setIme(ime);
		    u.setDatumRodjenja(datumRodjenja);
		    
		    Uloga role = ur.findById(2).get();
		    
			u.setUloga(role);
			role.addLaboratorijaKorisnik(u);
		    
		    lkr.save(u);
		    return "/index";
		}
	 	

		@RequestMapping(value = "logout", method = RequestMethod.GET)
		public String logout() {
			System.err.println("-------------------------------------------------------------------------");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(null);
			}
			
			return "redirect:/controller/loginUser";
		}
	 
	 @ResponseBody
	 @RequestMapping(value = "/pocetna" , method = RequestMethod.GET)
	 public void pocenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Principal principal = request.getUserPrincipal();
		 String brLicneKarte = principal.getName();
		 Laboratorija_korisnik lk = lkr.findBybrLicneKarte(brLicneKarte).get();
		 request.getSession().setAttribute("logovanKorisnik", lk);
		 request.getRequestDispatcher("/index.jsp").forward(request, response);
	 }
}
