package dev.controller.vm;

import java.time.LocalDate;

import dev.domain.Mission;

/**
 * @author janka
 *
 */
public class EventVM {
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String nature;

	/**
	 * 
	 */
	public EventVM(Mission mission) {
		this.dateDebut = mission.getDateDebut();
		this.dateFin = mission.getDateFin();
		this.nature = mission.getNature().getLibelle();
	}



	/**
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
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
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

}
