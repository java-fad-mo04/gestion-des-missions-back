package dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import dev.domain.Mission;
import dev.service.MissionService;

public class MissionController {
	private MissionService missionService;

	/**
	 * @param missionService
	 */
	public MissionController(MissionService missionService) {
		super();
		this.missionService = missionService;
	}

	@PostMapping
	public ResponseEntity<String> createMission(Mission missionIn) {
		return this.missionService.createMission(missionIn);
	}
}
