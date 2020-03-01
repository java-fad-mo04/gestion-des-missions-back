package dev.utils;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class with methods checking if the date is a public Holiday in France, or
 * if it is today
 * 
 * @author janka
 *
 */
public class DateChecker {
	private static final Logger LOG = LoggerFactory.getLogger(DateChecker.class);

	/**
	 * Check if the start/end date of the mission is on the weekend or holiday
	 * 
	 * @param dateDebut
	 *            start date of the mission
	 * @param dateFin
	 *            end date of the mission
	 * @return true/false
	 */
	public static boolean isHoliday(LocalDate dateDebut, LocalDate dateFin) {
		List<LocalDate> holidays = getHolidays();
		boolean notWorkday = false;
		for (LocalDate l : holidays) {
			if (dateDebut.isEqual(l) || dateFin.isEqual(l)) {
				notWorkday = true;
			}
			if (dateDebut.getDayOfWeek().equals(DayOfWeek.SATURDAY) || dateDebut.getDayOfWeek().equals(DayOfWeek.SUNDAY)
					|| dateFin.getDayOfWeek().equals(DayOfWeek.SATURDAY)
					|| dateFin.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				notWorkday = true;
			}
		}
		return notWorkday;
	}

	/**
	 * Method parses html page with jsoup
	 * @return list of official holidays in France except additional holidays in Alsace ftm
	 */
	public static List<LocalDate> getHolidays() {
		try {
			String url = "https://inspection-du-travail.com/jours-feries/";

			Document doc = Jsoup.connect(url).get();
			Elements rows = doc.select("tr");
			Elements columns = rows.get(0).select("td");

			List<String> years = new ArrayList<String>();
			for (int j = 1; j < columns.size(); j++) {
				String y[] = columns.get(j).text().split(" ");
				years.add(y[1]);
			}

			List<LocalDate> holidays = new ArrayList<>();
			for (int i = 1; i < rows.size() - 3; i++) {
				Elements cols = rows.get(i).select("td");
				int k = 0;
				for (int j = 1; j < cols.size(); j++) {
					String bar[] = cols.get(j).text().split(" ");
					String entry = bar[1].replace("er", "") + " " + bar[2].toLowerCase() + " " + years.get(k);
					k++;

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRANCE);
					holidays.add(LocalDate.parse(entry, formatter));
				}

			}
			return holidays;
		} catch (IOException | DateTimeParseException e) {
			LOG.error(e.getMessage());
			return null;
		}

	}

	/**
	 * Method checks if the mission starts today
	 * @param dateDebut start day of the mission
	 * @returns true/false
	 */
	public static boolean isToday(LocalDate dateDebut) {
		LocalDate today = LocalDate.now(ZoneId.of("Europe/Paris"));
		if (dateDebut.isEqual(today)) {
			return true;
		}
		return false;
	}

}
