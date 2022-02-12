package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Laboratorija_korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Laboratorija_korisnik.findAll", query="SELECT l FROM Laboratorija_korisnik l")
public class Laboratorija_korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String brLicneKarte;

	@Column(name="datum_rodjenja")
	private String datumRodjenja;

	private String ime;

	private String kontakt;

	private String prezime;

	private String sifra;


	//bi-directional many-to-one association to Laboratorija_korisnik
	@ManyToOne
	@JoinColumn(name="idUloga", insertable = false, updatable = false)
	private Laboratorija_korisnik laboratorijaKorisnik;

	//bi-directional many-to-one association to Laboratorija_korisnik
	@OneToMany(mappedBy="laboratorijaKorisnik")
	private List<Laboratorija_korisnik> laboratorijaKorisniks;

	//bi-directional many-to-one association to Uloga
	@ManyToOne
	@JoinColumn(name="idUloga")
	private Uloga uloga;

	//bi-directional many-to-one association to Rezervacija
	@OneToMany(mappedBy="laboratorijaKorisnik")
	private List<Rezervacija> rezervacijas;

	public Laboratorija_korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getBrLicneKarte() {
		return this.brLicneKarte;
	}

	public void setBrLicneKarte(String brLicneKarte) {
		this.brLicneKarte = brLicneKarte;
	}

	public String getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getKontakt() {
		return this.kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getSifra() {
		return this.sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	Laboratorija_korisnik getLaboratorijaKorisnik() {
		return this.laboratorijaKorisnik;
	}

	public void setLaboratorijaKorisnik(Laboratorija_korisnik laboratorijaKorisnik) {
		this.laboratorijaKorisnik = laboratorijaKorisnik;
	}

	public List<Laboratorija_korisnik> getLaboratorijaKorisniks() {
		return this.laboratorijaKorisniks;
	}

	public void setLaboratorijaKorisniks(List<Laboratorija_korisnik> laboratorijaKorisniks) {
		this.laboratorijaKorisniks = laboratorijaKorisniks;
	}

	public Laboratorija_korisnik addLaboratorijaKorisnik(Laboratorija_korisnik laboratorijaKorisnik) {
		getLaboratorijaKorisniks().add(laboratorijaKorisnik);
		laboratorijaKorisnik.setLaboratorijaKorisnik(this);

		return laboratorijaKorisnik;
	}

	public Laboratorija_korisnik removeLaboratorijaKorisnik(Laboratorija_korisnik laboratorijaKorisnik) {
		getLaboratorijaKorisniks().remove(laboratorijaKorisnik);
		laboratorijaKorisnik.setLaboratorijaKorisnik(null);

		return laboratorijaKorisnik;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Rezervacija> getRezervacijas() {
		return this.rezervacijas;
	}

	public void setRezervacijas(List<Rezervacija> rezervacijas) {
		this.rezervacijas = rezervacijas;
	}

	public Rezervacija addRezervacija(Rezervacija rezervacija) {
		getRezervacijas().add(rezervacija);
		rezervacija.setLaboratorijaKorisnik(this);

		return rezervacija;
	}

	public Rezervacija removeRezervacija(Rezervacija rezervacija) {
		getRezervacijas().remove(rezervacija);
		rezervacija.setLaboratorijaKorisnik(null);

		return rezervacija;
	}

}