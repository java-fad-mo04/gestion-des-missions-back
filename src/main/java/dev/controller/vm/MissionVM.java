package dev.controller.vm;

import java.time.LocalDate;

import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Status;
import dev.domain.Transport;

public class MissionVM {
	private Long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Nature nature;
	private String villeDepart;
	private String villeArrivee;
	private Transport transport;
	private Status status;
	private CollegueVM collegue;

	/**
	 * Cnstructor
	 * 
	 * @param mission
	 *            missionVM
	 */
	public MissionVM(Mission mission) {
		super();
		this.id = mission.getId();
		this.dateDebut = mission.getDateDebut();
		this.dateFin = mission.getDateFin();
		this.nature = mission.getNature();
		this.villeDepart = mission.getVilleDepart();
		this.villeArrivee = mission.getVilleArrivee();
		this.transport = mission.getTransport();
		this.status = mission.getStatus();
		this.collegue = new CollegueVM(mission.getCollegue());
	}

	/**
	 * Constructor
	 * 
	 */
	public MissionVM() {
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
	 * @return the nature
	 */
	public Nature getNature() {
		return nature;
	}

	/**
	 * @param nature
	 *            the nature to set
	 */
	public void setNature(Nature nature) {
		this.nature = nature;
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
	 * @return the transport
	 */
	public Transport getTransport() {
		return transport;
	}

	/**
	 * @param transport
	 *            the transport to set
	 */
	public void setTransport(Transport transport) {
		this.transport = transport;
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
	 * @return the collegue
	 */
	public CollegueVM getCollegue() {
		return collegue;
	}

	/**
	 * @param collegue
	 *            the collegue to set
	 */
	public void setCollegue(CollegueVM collegue) {
		this.collegue = collegue;
	}

}
