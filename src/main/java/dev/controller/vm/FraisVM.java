/**
 * 
 */
package dev.controller.vm;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.domain.LigneDeFrais;
import dev.domain.Mission;
import dev.domain.NatureFrais;

/**
 * @author clementlittardi
 *
 */
public class FraisVM {
	private long id;
	private LocalDate date;
	private NatureFrais nature;
	private long montant;
	private Mission mission;
	
	public FraisVM(LigneDeFrais ligneDeFrais) {
		super();
		this.id=id;
		this.date=date;
		this.montant=montant;
		this.nature=nature;
		this.mission=mission;
	}
	
	public FraisVM() {
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the date
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
	 * @param nature the nature to set
	 */
	public void setNature(NatureFrais nature) {
		this.nature = nature;
	}

	/**
	 * @return the montant
	 */
	public long getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(long montant) {
		this.montant = montant;
	}

	/**
	 * @return the mission
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
