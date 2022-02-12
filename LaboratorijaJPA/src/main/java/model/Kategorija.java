package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Kategorija database table.
 * 
 */
@Entity
@NamedQuery(name="Kategorija.findAll", query="SELECT k FROM Kategorija k")
public class Kategorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKategorija;

	private String naziv;

	//bi-directional many-to-one association to Analiza
	@OneToMany(mappedBy="kategorija")
	private List<Analiza> analizas;


	public Kategorija() {
	}

	public int getIdKategorija() {
		return this.idKategorija;
	}

	public void setIdKategorija(int idKategorija) {
		this.idKategorija = idKategorija;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Analiza> getAnalizas() {
		return this.analizas;
	}

	public void setAnalizas(List<Analiza> analizas) {
		this.analizas = analizas;
	}

	public Analiza addAnaliza(Analiza analiza) {
		getAnalizas().add(analiza);
		analiza.setKategorija(this);

		return analiza;
	}

	public Analiza removeAnaliza(Analiza analiza) {
		getAnalizas().remove(analiza);
		analiza.setKategorija(null);

		return analiza;
	}


}