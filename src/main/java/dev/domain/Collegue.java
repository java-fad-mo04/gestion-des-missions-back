package dev.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * A class that represents a colleague.
 * 
 * @author janka
 */

@Entity
public class Collegue {

    /** identification */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** last name of the colleague */
    private String nom;
    /** first name of the colleague */
    private String prenom;
    /** email of the colleague */
    private String email;
    /** password of the colleague */
    private String motDePasse;

    @OneToMany(mappedBy = "collegue", cascade = CascadeType.PERSIST)
    private List<RoleCollegue> roles;

	/**
	 * @return the id identification
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id identification to set  
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nom - last name
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the last name to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return prenom the first name
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the first name to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the email email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the motDePasse password
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse the password to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the roles colleague roles 
	 */
	public List<RoleCollegue> getRoles() {
		return roles;
	}

	/**
	 * @param roles the colleague roles to set
	 */
	public void setRoles(List<RoleCollegue> roles) {
		this.roles = roles;
	}


}
