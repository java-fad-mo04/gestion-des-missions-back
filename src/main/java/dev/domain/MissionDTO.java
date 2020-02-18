package dev.domain;

import java.time.LocalDate;

public class MissionDTO {
	private Long id;
	/**
	 * start date of the mission
	 */
	private LocalDate dateDebut;

	/** end date of the mission */
	private LocalDate dateFin;

	/** city of departure */
	private String villeDepart;

	/** city of arrival */
	private String villeArrivee;

	/** nature of the mission */
	private Long natureId;

	/** transport used */
	private Long transportId;

	/** validation status */
	private Status status;

	/** a colleague on the mission */
	private Long collegueId;

	/** expenses */
	private Long ficheDeFraisId;

	/**
	 * 
	 */
	public MissionDTO() {
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
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the villeDepart
	 */
	public String getVilleDepart() {
		return villeDepart;
	}

	/**
	 * @param villeDepart
	 *            the villeDepart to set
	 */
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	/**
	 * @return the villeArrivee
	 */
	public String getVilleArrivee() {
		return villeArrivee;
	}

	/**
	 * @param villeArrivee
	 *            the villeArrivee to set
	 */
	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	/**
	 * @return the natureId
	 */
	public Long getNatureId() {
		return natureId;
	}

	/**
	 * @param natureId
	 *            the natureId to set
	 */
	public void setNatureId(Long natureId) {
		this.natureId = natureId;
	}

	/**
	 * @return the transportId
	 */
	public Long getTransportId() {
		return transportId;
	}

	/**
	 * @param transportId
	 *            the transportId to set
	 */
	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}


	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the collegueId
	 */
	public Long getCollegueId() {
		return collegueId;
	}

	/**
	 * @param collegueId
	 *            the collegueId to set
	 */
	public void setCollegueId(Long collegueId) {
		this.collegueId = collegueId;
	}

	/**
	 * @return the ficheDeFraisId
	 */
	public Long getFicheDeFraisId() {
		return ficheDeFraisId;
	}

	/**
	 * @param ficheDeFraisId
	 *            the ficheDeFraisId to set
	 */
	public void setFicheDeFraisId(Long ficheDeFraisId) {
		this.ficheDeFraisId = ficheDeFraisId;
	}

}
