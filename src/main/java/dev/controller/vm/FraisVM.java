/**
 * 
 */
package dev.controller.vm;

import java.math.BigDecimal;
import java.time.LocalDate;

import dev.domain.LigneDeFrais;

/**
 * @author clementlittardi
 *
 */
public class FraisVM {
	private long id;
	private LocalDate date;
	private String libelle;
	private BigDecimal montant;
	private MissionVM mission;
	
	/**
	 * Constructeur
	 * 
	 * @param ligneDeFrais
	 *            ligne de frais
	 */
	public FraisVM(LigneDeFrais ligneDeFrais) {
		super();
		this.id = ligneDeFrais.getId();
		this.date = ligneDeFrais.getDate();
		this.montant = ligneDeFrais.getMontant();
		this.libelle = ligneDeFrais.getLibelle();
		this.mission = new MissionVM(ligneDeFrais.getMission());
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
	 * @param montant the montant to set
	 */
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	/**
	 * @return the mission
	 */
	public MissionVM getMission() {
		return mission;
	}

	/**
	 * @param mission the mission to set
	 */
	public void setMission(MissionVM mission) {
		this.mission = mission;
	}

	
	
}
