package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Uloga database table.
 * 
 */
@Entity
@NamedQuery(name="Uloga.findAll", query="SELECT u FROM Uloga u")
public class Uloga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUloga;

	private String naziv;

	//bi-directional many-to-one association to Laboratorija_korisnik
	@OneToMany(mappedBy="uloga")
	private List<Laboratorija_korisnik> laboratorijaKorisniks;

	public Uloga() {
	}

	public int getIdUloga() {
		return this.idUloga;
	}

	public void setIdUloga(int idUloga) {
		this.idUloga = idUloga;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Laboratorija_korisnik> getLaboratorijaKorisniks() {
		return this.laboratorijaKorisniks;
	}

	public void setLaboratorijaKorisniks(List<Laboratorija_korisnik> laboratorijaKorisniks) {
		this.laboratorijaKorisniks = laboratorijaKorisniks;
	}

	public Laboratorija_korisnik addLaboratorijaKorisnik(Laboratorija_korisnik laboratorijaKorisnik) {
		getLaboratorijaKorisniks().add(laboratorijaKorisnik);
		laboratorijaKorisnik.setUloga(this);

		return laboratorijaKorisnik;
	}

	public Laboratorija_korisnik removeLaboratorijaKorisnik(Laboratorija_korisnik laboratorijaKorisnik) {
		getLaboratorijaKorisniks().remove(laboratorijaKorisnik);
		laboratorijaKorisnik.setUloga(null);

		return laboratorijaKorisnik;
	}

}