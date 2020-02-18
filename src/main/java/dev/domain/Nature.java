package dev.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;

import javax.validation.constraints.NotNull;

/**
 * A class that represents nature of the mission
 * @author janka
 *
 */
@Entity
public class Nature {
	/** identification */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** label */
	@NotNull
	private String libelle;

	/** if the mission is payed	 */
	@NotNull
	private boolean estFacture;

	/** if there is a bonus	 */
	@NotNull
	private boolean estPrime;

	/** average daily rate */
	private double tjm;

	/** bonus sum */
	@Max(10)
	private BigDecimal valeurPrime;

	/** end date */
	private LocalDate dateFin;


	/** constructor */
	public Nature() {
	}

	/**
	 * @param libelle label
	 * @param estFacture if payed
	 * @param estPrime if there's a bonus
	 * @param tjm average daily rate
	 * @param valeurPrime bonus sum
	 * @param dateFin end date
	 */
	public Nature(String libelle, boolean estFacture, boolean estPrime, double tjm, BigDecimal valeurPrime) {
		super();
		this.libelle = libelle;
		this.estFacture = estFacture;
		this.estPrime = estPrime;
		this.tjm = tjm;
		this.valeurPrime = valeurPrime;
	}
	
	

	/**
	 * @return the id identification
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the libelle label
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the label to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the estFacture is payed or not
	 */
	public boolean isEstFacture() {
		return estFacture;
	}

	/**
	 * @param estFacture the payed to set
	 */
	public void setEstFacture(boolean estFacture) {
		this.estFacture = estFacture;
	}

	/**
	 * @return the estPrime there is a bonus
	 */
	public boolean isEstPrime() {
		return estPrime;
	}

	/**
	 * @param estPrime the estPrime to set
	 */
	public void setEstPrime(boolean estPrime) {
		this.estPrime = estPrime;
	}

	/**
	 * @return the tjm average daily rate
	 */
	public double getTjm() {
		return tjm;
	}

	/**
	 * @param tjm the average daily rate to set
	 */
	public void setTjm(double tjm) {
		this.tjm = tjm;
	}

	/**
	 * @return the valeurPrime bonus sum
	 */
	public BigDecimal getValeurPrime() {
		return valeurPrime;
	}

	/**
	 * @param valeurPrime the bonus sum to set
	 */
	public void setValeurPrime(BigDecimal valeurPrime) {
		this.valeurPrime = valeurPrime;
	}

	/**
	 * @return the dateFin end date
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the end date to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

}