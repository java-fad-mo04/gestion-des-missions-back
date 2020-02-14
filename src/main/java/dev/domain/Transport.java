package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A class that represents transport
 * @author janka
 *
 */
@Entity
public class Transport {
	 /** identification number  */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** label	 */
	private String libelle;

	 /** constructor  */
	public Transport() {
	}

	/**
	 *  constructor 
	 * @param libelle label
	 */
	public Transport(String libelle) {
		super();
		this.libelle = libelle;
	}

	/**
	 * @return the id id number
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

}
