package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Rezervacija database table.
 * 
 */
@Entity
@NamedQuery(name="Rezervacija.findAll", query="SELECT r FROM Rezervacija r")
public class Rezervacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRezervacija;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private int idLaborant;

	//bi-directional many-to-one association to Analiza
	@OneToMany(mappedBy="rezervacija")
	private List<Analiza> analizas;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="rezervacija")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Analiza
	@ManyToOne
	@JoinColumn(name="idAnaliza")
	private Analiza analiza;

	//bi-directional many-to-one association to Laboratorija_korisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik")
	private Laboratorija_korisnik laboratorijaKorisnik;

	public Rezervacija() {
	}

	public int getIdRezervacija() {
		return this.idRezervacija;
	}

	public void setIdRezervacija(int idRezervacija) {
		this.idRezervacija = idRezervacija;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getIdLaborant() {
		return this.idLaborant;
	}

	public void setIdLaborant(int idLaborant) {
		this.idLaborant = idLaborant;
	}

	public List<Analiza> getAnalizas() {
		return this.analizas;
	}

	public void setAnalizas(List<Analiza> analizas) {
		this.analizas = analizas;
	}

	public Analiza addAnaliza(Analiza analiza) {
		getAnalizas().add(analiza);
		analiza.setRezervacija(this);

		return analiza;
	}

	public Analiza removeAnaliza(Analiza analiza) {
		getAnalizas().remove(analiza);
		analiza.setRezervacija(null);

		return analiza;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setRezervacija(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setRezervacija(null);

		return komentar;
	}

	public Analiza getAnaliza() {
		return this.analiza;
	}

	public void setAnaliza(Analiza analiza) {
		this.analiza = analiza;
	}

	public Laboratorija_korisnik getLaboratorijaKorisnik() {
		return this.laboratorijaKorisnik;
	}

	public void setLaboratorijaKorisnik(Laboratorija_korisnik laboratorijaKorisnik) {
		this.laboratorijaKorisnik = laboratorijaKorisnik;
	}

}