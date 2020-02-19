package dev.domain;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author janka
 *
 */
public class Holidays {
	private UUID uuid;
	private String name;
	private LocalDate date;// "2019-12-25",
	private LocalDate observed;
	private boolean isPublic;
	private String country;
	private Weekday weekday;

	/**
	 * Constructor
	 */
	public Holidays() {
	}

	/**
	 * @param uuid
	 * @param name
	 * @param date
	 * @param observed
	 * @param isPublic
	 * @param country
	 * @param weekday
	 */
	public Holidays(String name, LocalDate date, LocalDate observed, boolean isPublic, String country,
			Weekday weekday) {
		super();
		this.name = name;
		this.date = date;
		this.observed = observed;
		this.isPublic = isPublic;
		this.country = country;
		this.weekday = weekday;
	}

	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the observed
	 */
	public LocalDate getObserved() {
		return observed;
	}

	/**
	 * @param observed
	 *            the observed to set
	 */
	public void setObserved(LocalDate observed) {
		this.observed = observed;
	}

	/**
	 * @return the isPublic
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the weekday
	 */
	public Weekday getWeekday() {
		return weekday;
	}

	/**
	 * @param weekday
	 *            the weekday to set
	 */
	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}
}
