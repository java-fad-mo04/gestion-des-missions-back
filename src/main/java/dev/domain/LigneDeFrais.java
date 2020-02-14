package dev.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * A class that represents Expense sheet
 * @author janka
 *
 */
@Entity
public class LigneDeFrais {

	/** identification number */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** date */
	private LocalDate date;

	/** label */
	private String libelle;

	/** total sum	 */
	private BigDecimal montant;

	/** mission	 */
	@OneToOne
	private Mission mission;

	/**
	 * Constructor
	 */
	public LigneDeFrais() {

	}

	/**
	 * @param date date
	 * @param libelle label
	 * @param montant total sum
	 * @param mission mission
	 */
	public LigneDeFrais(Long id, LocalDate date, String libelle, BigDecimal montant, Mission mission) {
		super();
		this.id=id;
		this.date = date;
		this.libelle = libelle;
		this.montant = montant;
		this.mission = mission;
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
	 * @return the date date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
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
	 * @return the montant total sum
	 */
	public BigDecimal getMontant() {
		return montant;
	}

	/**
	 * @param montant the total sum to set
	 */
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	/**
	 * @return the mission mission
	 */
	public Mission getMission() {
		return mission;
	}

	/**
	 * @param mission the mission to set
	 */
	public void setMission(Mission mission) {
		this.mission = mission;
	}


}
