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
	private NatureFrais nature;

	/** total sum	 */
	private long montant;

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
	public LigneDeFrais(LocalDate date, NatureFrais nature, long montant, Mission mission) {
		super();
		this.date = date;
		this.nature = nature;
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
	 * @return the nature 
	 */
	public NatureFrais getNature() {
		return nature;
	}

	/**
	 * @param libelle the label to set
	 */
	public void setNature(NatureFrais nature) {
		this.nature = nature;
	}

	/**
	 * @return the montant total sum
	 */
	public long getMontant() {
		return montant;
	}

	/**
	 * @param montant the total sum to set
	 */
	public void setMontant(long montant) {
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
