package dev.domain;

import javax.persistence.*;

/**
 * Mapping class between enum Role and Collegue
 * @author janka
 *
 */
@Entity
public class RoleCollegue {

    /** identification number */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** colleague    */
    @ManyToOne
    @JoinColumn(name = "collegue_id")
    private Collegue collegue;
    /** role   */
    @Enumerated(EnumType.STRING)
    private Role role;
    /** constructor  */
    public RoleCollegue() {
    }
    /** constructor  */
    public RoleCollegue(Collegue collegue, Role role) {
        this.collegue = collegue;
        this.role = role;
    }
	/**
	 * @return the id identification number
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
	 * @return the collegue collegue
	 */
	public Collegue getCollegue() {
		return collegue;
	}
	/**
	 * @param collegue the collegue to set
	 */
	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}
	/**
	 * @return the role role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

   
}
