package dev.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * A class that represents a mission
 * 
 * @author janka
 */
@Entity
public class Mission {
	/** identification number */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * start date of the mission
	 */
	private LocalDate dateDebut;

	/** end date of the mission	 */
	private LocalDate dateFin;

	/** city of departure */
	private String villeDepart;

	/** city of arrival */
	private String villeArrivee;

	/** nature of the mission	 */
	@OneToOne
	private Nature nature;

	/** transport used */
	@OneToOne
	private Transport transport;

	/** validation status */
	@Enumerated(EnumType.STRING)
	private Status status;

	/** a colleague on the mission */
	@OneToOne
	@JsonManagedReference
	private Collegue collegue;

	/** expenses */
	@OneToOne
	private LigneDeFrais ficheDeFrais;

	/** Constructor	 */
	public Mission() {
		super();
	}

	/**
	 * @param dateDebut
	 *            start date
	 * @param dateFin
	 *            end date
	 * @param nature
	 *            nature
	 * @param transport
	 *            transport
	 * @param status
	 *            validation status
	 * @param collegue
	 *            colleague
	 * @param ficheDeFrais
	 */
	public Mission(LocalDate dateDebut, LocalDate dateFin, String villeDepart, String villeArrivee, Nature nature,
			Transport transport, Status status, Collegue collegues, LigneDeFrais ficheDeFrais) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.nature = nature;
		this.transport = transport;
		this.status = status;
		this.collegue = collegues;
		this.ficheDeFrais = ficheDeFrais;
	}

	/**
	 * @return the id identification number
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id  the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the dateDebut start date
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the start date to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin the end date
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the end date to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the nature nature of the mission
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
	 * @return the transport transport
	 */
	public Transport getTransport() {
		return transport;
	}

	/**
	 * @param transport  the transport to set
	 */
	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	/**
	 * @return the status validation status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the collegue colleague
	 */
	public Collegue getCollegue() {
		return collegue;
	}

	/**
	 * @param collegue
	 *            the colleague to set
	 */
	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
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
	 * @return the ficheDeFrais
	 */
	public LigneDeFrais getFicheDeFrais() {
		return ficheDeFrais;
	}

	/**
	 * @param ficheDeFrais
	 *            the ficheDeFrais to set
	 */
	public void setFicheDeFrais(LigneDeFrais ficheDeFrais) {
		this.ficheDeFrais = ficheDeFrais;
	}

}
