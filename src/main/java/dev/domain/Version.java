package dev.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

/**
 * A class that represents Version
 * 
 * @author janka
 *
 */
@Entity
public class Version {

	 /** id number  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /** date and time created  */
    private ZonedDateTime dateTimeCreated;
    /** git version  */
    private String gitVersion;
    /** constructor  */
    public Version() {
    }

    /**
     * constructor
     * @param gitVersion
     */
    public Version(String gitVersion) {
        this.gitVersion = gitVersion;
    }

    @PrePersist
    public void createDefaultDateTimeCreated() {
        this.dateTimeCreated = ZonedDateTime.now();
    }

    /**
	 * @return the id id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the dateTimeCreated
	 */
	public ZonedDateTime getDateTimeCreated() {
		return dateTimeCreated;
	}

	/**
	 * @param dateTimeCreated the dateTimeCreated to set
	 */
	public void setDateTimeCreated(ZonedDateTime dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}

	/**
	 * @return the gitVersion
	 */
	public String getGitVersion() {
		return gitVersion;
	}

	/**
	 * @param gitVersion the gitVersion to set
	 */
	public void setGitVersion(String gitVersion) {
		this.gitVersion = gitVersion;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version that = (Version) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateTimeCreated, that.dateTimeCreated) &&
                Objects.equals(gitVersion, that.gitVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTimeCreated, gitVersion);
    }
}
