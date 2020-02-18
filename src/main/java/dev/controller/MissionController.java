package dev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.MissionDTO;
import dev.service.MissionService;

@CrossOrigin
@RestController
@RequestMapping("mission")
public class MissionController {
	private MissionService missionService;

	/**
	 * @param missionService
	 */
	public MissionController(MissionService missionService) {
		super();
		this.missionService = missionService;
	}

	@GetMapping
	public List<MissionDTO> listMission() {
		return this.missionService.listMission();
	}

	@PostMapping()
	public ResponseEntity<String> createMission(@RequestBody @Valid MissionDTO missionIn) {
		return this.missionService.createMission(missionIn);
	}
}
