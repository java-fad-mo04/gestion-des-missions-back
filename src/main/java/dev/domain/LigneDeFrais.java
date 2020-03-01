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
	 * Constructor with params
	 * 
	 * @param date date
	 * @param libelle label
	 * @param montant sum
	 * @param mission mission
	 */
	public LigneDeFrais(LocalDate date, String libelle, BigDecimal montant, Mission mission) {
		super();
		this.date = date;
		this.libelle = libelle;
		this.montant = montant;
		this.mission = mission;
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
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the montant
	 */
	public BigDecimal getMontant() {
		return montant;
	}

	/**
	 * @param montant
	 *            the montant to set
	 */
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
