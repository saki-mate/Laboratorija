package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Analiza database table.
 * 
 */
@Entity
@NamedQuery(name="Analiza.findAll", query="SELECT a FROM Analiza a")
public class Analiza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAnaliza;

	private int cena;

	private String naziv;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	@JoinColumn(name="idKategorija")
	private Kategorija kategorija;

	//bi-directional many-to-one association to Rezervacija
	@ManyToOne
	@JoinColumn(name="idRezervacija")
	private Rezervacija rezervacija;

	//bi-directional many-to-one association to Rezervacija
	@OneToMany(mappedBy="analiza")
	private List<Rezervacija> rezervacijas;

	public Analiza() {
	}

	public int getIdAnaliza() {
		return this.idAnaliza;
	}

	public void setIdAnaliza(int idAnaliza) {
		this.idAnaliza = idAnaliza;
	}

	public int getCena() {
		return this.cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public Rezervacija getRezervacija() {
		return this.rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public List<Rezervacija> getRezervacijas() {
		return this.rezervacijas;
	}

	public void setRezervacijas(List<Rezervacija> rezervacijas) {
		this.rezervacijas = rezervacijas;
	}

	public Rezervacija addRezervacija(Rezervacija rezervacija) {
		getRezervacijas().add(rezervacija);
		rezervacija.setAnaliza(this);

		return rezervacija;
	}

	public Rezervacija removeRezervacija(Rezervacija rezervacija) {
		getRezervacijas().remove(rezervacija);
		rezervacija.setAnaliza(null);

		return rezervacija;
	}

}