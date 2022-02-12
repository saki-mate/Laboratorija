package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Termin database table.
 * 
 */
@Entity
@NamedQuery(name="Termin.findAll", query="SELECT t FROM Termin t")
public class Termin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTermin;

	private String vreme;

	private int zauzetost;

	//bi-directional many-to-one association to Laboratorija_korisnik
	@ManyToOne
	@JoinColumn(name="idLaborant")
	private Laboratorija_korisnik laboratorijaKorisnik;

	public Termin() {
	}

	public int getIdTermin() {
		return this.idTermin;
	}

	public void setIdTermin(int idTermin) {
		this.idTermin = idTermin;
	}

	public String getVreme() {
		return this.vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public int getZauzetost() {
		return this.zauzetost;
	}

	public void setZauzetost(int zauzetost) {
		this.zauzetost = zauzetost;
	}

	public Laboratorija_korisnik getLaboratorijaKorisnik() {
		return this.laboratorijaKorisnik;
	}

	public void setLaboratorijaKorisnik(Laboratorija_korisnik laboratorijaKorisnik) {
		this.laboratorijaKorisnik = laboratorijaKorisnik;
	}

}