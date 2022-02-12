package com.example.demo.controller.laborant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.AnalizaReposirtory;
import com.example.demo.repository.KategorijaRepository;
import com.example.demo.repository.KomentarRepository;
import com.example.demo.repository.Laboratorija_korisnikReposiroty;
import com.example.demo.repository.RezervacijaRepository;
import com.example.demo.repository.UlogaRepository;

import model.Analiza;
import model.Kategorija;
import model.Komentar;
import model.Laboratorija_korisnik;
import model.Rezervacija;
import model.Uloga;

@Controller
@RequestMapping(value = "/laborantController")
public class LaborantController {

	@Autowired
	KategorijaRepository kr;

	@Autowired
	AnalizaReposirtory ar;
	
	@Autowired
	Laboratorija_korisnikReposiroty lk;
	
	@Autowired
	UlogaRepository ur;
	
	@Autowired
	KomentarRepository kor;
	
	@Autowired
	RezervacijaRepository rr;
	
	@RequestMapping(value = "/sveKategorije")
	public String sviReagensi(HttpServletRequest request) {
		List<Kategorija> listaK = kr.findAll();
		request.getSession().setAttribute("kategorije", listaK);
		// podrazumeva se da je .jsp jer smo u aplication properties to stavili
		return "laborant/dodavanjeAnalize";
	}
	@RequestMapping(value = "/sviLaboranti")
	public String sviLaboranti(HttpServletRequest request) {
		Uloga u = ur.findById(1).get();
		
		List<Laboratorija_korisnik> listaLK = lk.findByUloga(u);
		request.getSession().setAttribute("laboranti", listaLK);
		// podrazumeva se da je .jsp jer smo u aplication properties to stavili
		return "klijent/prikazi_analizu";
	}
	
	@RequestMapping(value = "/sviKlijenti")
	public String sviKlijenti(HttpServletRequest request) {
		Uloga u = ur.findById(2).get();
		
		List<Laboratorija_korisnik> listaK = lk.findByUloga(u);
		request.getSession().setAttribute("klijenti", listaK);
		// podrazumeva se da je .jsp jer smo u aplication properties to stavili
		//VIDI GDE CES GA VRATITI
		return "laborant/prikaziKlijente";
	}
	@RequestMapping(value = "/sveUloge")
	public String sveULoge(HttpServletRequest request) {
		
		List<Uloga> listaU = ur.findAll();
		request.getSession().setAttribute("uloge", listaU);
		// podrazumeva se da je .jsp jer smo u aplication properties to stavili
		//VIDI GDE CES GA VRATITI
		return "laborant/registracija";
	}
	// Dobavimo analizu iz comboBoxa jednu analizu i nakon toga, stavimo u sesiju
	@RequestMapping(value = "/prikaziKategoriju")
	public String prikaziReagens(HttpServletRequest request, Integer cbKategorija) {
		Kategorija k = kr.findById(cbKategorija).get();
		request.getSession().setAttribute("kategorija", k);
		return "laborant/dodavanjaAnalize";
	}
	
	@RequestMapping(value = "/dodajAnalizu", method = RequestMethod.POST)
	public String dodajAnalizu(HttpServletRequest request, Integer cena, String naziv, Integer cbKategorija) {
			
			Analiza a = new Analiza();
			Kategorija k = kr.findById(cbKategorija).get();
			System.out.println("Odabrana kategorija je: " + k.toString());
			a.setCena(cena);
			a.setNaziv(naziv);
			a.setKategorija(k);

			ar.save(a);
			
			request.getSession().setAttribute("analiza", a);
			System.out.println("Analiza je uspesno sacuvana.");
			
			return "laborant/dodavanjeAnalize";
		}
	@RequestMapping(value = "/promeniCenu", method = RequestMethod.POST)
	public String promeniCenu(Integer cenaNova, Integer cbAnalize) {
		Analiza a = ar.findById(cbAnalize).get();
		System.out.println("pronasao sam analizu a");
		a.setCena(cenaNova);
		ar.save(a);
		return "laborant/promenaCeneAnalize";
	}
	@RequestMapping(value = "/sveAnalizeL")
	public String sveAnalize(HttpServletRequest request) {
		List<Analiza> lista = ar.findAll();
		request.getSession().setAttribute("analize", lista);
		return "laborant/promenaCeneAnalize";
	}
	
	@RequestMapping(value = "/sviKomentari")
	public String sviKomentari(HttpServletRequest request) {
		List<Komentar> listaK = kor.findAll();
		request.getSession().setAttribute("sviKomentari", listaK);
		return "laborant/sviKomentari";
	}
	
	@RequestMapping(value = "/vidiBlizeKlijenta")
	public String vidiBlizeKlijenta(HttpServletRequest request, Integer cbKlijent) {
		Laboratorija_korisnik k = lk.findById(cbKlijent).get();
		Laboratorija_korisnik laborant = (Laboratorija_korisnik) request.getSession().getAttribute("logovanKorisnik");
		List<Rezervacija> listaR = rr.findByidKorisnikAndLaborant(k.getIdKorisnik(), laborant.getIdKorisnik());
		request.getSession().setAttribute("rezervacije", listaR);
		
		return "laborant/rezervacijeZaKlijenta";
	}
}
