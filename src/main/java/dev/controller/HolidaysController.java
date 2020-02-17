package dev.controller;

import java.util.List;

import dev.domain.Holidays;
import dev.service.HolidaysService;

public class HolidaysController {
	private HolidaysService holidaysService;

	/**
	 * @param holidaysService
	 */
	public HolidaysController(HolidaysService holidaysService) {
		super();
		this.holidaysService = holidaysService;
	}

	public List<Holidays> getHolidays() {
		return this.holidaysService.getHolidaysW();
	}

}
