package com.example.demo.controller.klijent;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.AnalizaReposirtory;
import com.example.demo.repository.KomentarRepository;
import com.example.demo.repository.Laboratorija_korisnikReposiroty;
import com.example.demo.repository.RezervacijaRepository;
import com.example.demo.repository.TerminRepository;
import com.example.demo.repository.UlogaRepository;

import model.Analiza;
import model.Komentar;
import model.Laboratorija_korisnik;
import model.Rezervacija;
import model.Termin;
import model.Uloga;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/klijentController")
public class KlijentController {
	@Autowired
	AnalizaReposirtory ar;
	
	@Autowired
	UlogaRepository ur;
	
	@Autowired
	Laboratorija_korisnikReposiroty lkr;
	
	@Autowired
	TerminRepository tr;
	
	@Autowired 
	RezervacijaRepository rr;
	
	@Autowired
	KomentarRepository kr;
	
		@RequestMapping(value = "/sveAnalize")
		public String sveAnalize(HttpServletRequest request) {
			List<Analiza> lista = ar.findAll();
			request.getSession().setAttribute("analize", lista);
			return "klijent/prikazi_analizu";
		}
		
		@RequestMapping(value = "/analizeKategorije")
		public String analizeKategorije(HttpServletRequest request) {
			
			return "klijent/prikaziKategorije";
		}

		@RequestMapping(value = "/prikaziAnalizu")
		public String prikaziAnalizu(HttpServletRequest request, Integer cbAnaliza) {
			Analiza a = ar.findById(cbAnaliza).get();
			request.getSession().setAttribute("analiza", a);
			return "klijent/prikazi_analizu";
		}
		
		@RequestMapping(value = "/slobodniTermini")
		public String slobodniTermini(HttpServletRequest request) {
			List<Termin> listT = tr.findByZauzetost(0);
			request.getSession().setAttribute("slobodniTermini", listT);
			return "klijent/slobodniTermini";
			
		}
		@RequestMapping(value = "/SvislobodniTermini")
		public String SvislobodniTermini(HttpServletRequest request) {
			List<Termin> listT = tr.findByZauzetost(0);
			request.getSession().setAttribute("slobodniTermini", listT);
			return "klijent/sviSlobodniTermini";
			
		}
		@RequestMapping(value = "/sviLaboranti")
		public String sviLaboranti(HttpServletRequest request) {
			Uloga u = ur.findById(1).get();
			
			List<Laboratorija_korisnik> listaLK = lkr.findByUloga(u);
			request.getSession().setAttribute("laboranti", listaLK);
			return "klijent/prikazi_analizu";
		}
		
		 @RequestMapping(value = "/odabraneAnalize", method = RequestMethod.GET)
			public String odabraneAnalize(HttpServletRequest request) {
			 List<Analiza> listOdabranih = new ArrayList<Analiza>();
			 int suma=0;
			 
			 	String select[] = request.getParameterValues("checkButton"); 
			 		if (select != null && select.length != 0) {
			 				System.out.println("You have selected: ");
			 					for (int i = 0; i < select.length; i++) {
			 						int index = Integer.parseInt(select[i]);
			 						Analiza a = ar.findById(index).get();
			 						suma += a.getCena();
			 						listOdabranih.add(a);
			 					}
			 		}
				request.getSession().setAttribute("odabraneAnalize", listOdabranih);
				request.getSession().setAttribute("suma", suma);
			 	return "klijent/slobodniTermini";
		 }
		 @RequestMapping(value = "istorijaAnaliza", method = RequestMethod.GET)
		public String istorijaAnaliza(HttpServletRequest request) {
			 Laboratorija_korisnik klijent = (Laboratorija_korisnik) request.getSession().getAttribute("logovanKorisnik");
			 int korisnik = klijent.getIdKorisnik();
			 List<Rezervacija> listRez = rr.findByidKorisnik(korisnik);
			 request.getSession().setAttribute("istorijaAnaliza", listRez);
			 return "klijent/mojeAnalize";
		 }

		 @RequestMapping(value = "rezervacija", method = RequestMethod.GET)
			public String rezervacija(Integer idTermin, HttpServletRequest request) {
			 	Termin t = tr.findById(idTermin).get();
			 	@SuppressWarnings("unchecked")
				List<Analiza> listaAnaliza = (List<Analiza>) request.getSession().getAttribute("odabraneAnalize");
			 	Laboratorija_korisnik klijent = (Laboratorija_korisnik) request.getSession().getAttribute("logovanKorisnik");
			 	Laboratorija_korisnik laborant = t.getLaboratorijaKorisnik();
			 	
			 	Rezervacija r  = new Rezervacija();
			 	r.setAnalizas(listaAnaliza);
			 	r.setAnaliza(listaAnaliza.get(0));
			 	r.setIdLaborant(laborant.getIdKorisnik());
			 	r.setDatum(new Date());
			 	r.setLaboratorijaKorisnik(klijent);
			 	r.setKomentars(null);
			 	
			 	rr.save(r);
			 	t.setZauzetost(1);
			 	tr.save(t);
			 	
			 	return "index";
			}
		 @RequestMapping(value = "sveRezervacije", method = RequestMethod.GET)
		public String sveRezervacije(HttpServletRequest request) {
			 Laboratorija_korisnik klijent = (Laboratorija_korisnik) request.getSession().getAttribute("logovanKorisnik");
			 int korisnik = klijent.getIdKorisnik();
			 List<Rezervacija> listRez = rr.findByidKorisnik(korisnik);
			 request.getSession().setAttribute("listaRezervacija", listRez);
			 return "klijent/dodavanjeKomentara";
		 }
		 @RequestMapping(value = "kome", method = RequestMethod.GET)
			public String kome(HttpServletRequest request, String komentar) {
			 request.getSession().setAttribute("komentar", komentar);
			 return "klijent/dodavanjeKomentara";
			 
		 }
		 
		 @RequestMapping(value = "dodavanjeKomentara", method = RequestMethod.GET)
			public String dodavanjeKomentara(HttpServletRequest request, Integer idRegistracija) {
			 	String komentar = (String) request.getSession().getAttribute("komentar");
			 	System.out.println(komentar);
			 	
			 	Rezervacija r = rr.findById(idRegistracija).get();
			 	//List<Komentar> ListaK = new ArrayList<Komentar>();
			 	Komentar k = new Komentar();
			 	k.setOpis(komentar);
			 	k.setVreme(new Date());
			 	k.setRezervacija(r);
			 	
			 	kr.save(k);
			 	
			 	return "index";
			 }
		 
		 @RequestMapping(value="sveAnalizePDF", method=RequestMethod.GET) 
			public void generisiIzvestaj(HttpServletRequest request, HttpServletResponse response) throws Exception { 
				List<Analiza> analize = ar.findAll();
				
				response.setContentType("text/html");
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(analize);
				InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/izvestaj.jrxml");
				JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("imeLab", "SaraMED");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
				inputStream.close();
				
				
				response.setContentType("application/x-download");
				response.addHeader("Content-disposition", "attachment; filename=Izvestaj.pdf");
				OutputStream out = response.getOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperPrint,out);
			}
}
