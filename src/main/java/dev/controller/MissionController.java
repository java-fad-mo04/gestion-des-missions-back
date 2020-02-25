package dev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping (path="/{idMission}")
	public MissionDTO recupMission(@PathVariable("idMission") @Valid Long idMission) throws Exception {
		return this.missionService.recupMission(idMission);
	};

	@PostMapping()
	public ResponseEntity<String> createMission(@RequestBody @Valid MissionDTO missionIn) {
		return this.missionService.createMission(missionIn);
	}

	@PatchMapping
	public ResponseEntity<String> patchReservation(@RequestBody @Valid MissionDTO rectif) throws Exception {
		return this.missionService.modifierMission(rectif);

	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<String> erreurdate(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
