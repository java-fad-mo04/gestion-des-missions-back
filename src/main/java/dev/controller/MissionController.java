package dev.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Mission;
import dev.service.MissionService;

@RestController
@RequestMapping(path = "mission")
public class MissionController {

	private MissionService missionService;

	public MissionController(MissionService missionService) {
		super();
		this.missionService = missionService;
	}

	@PatchMapping
	public ResponseEntity<?> patchReservation(@RequestBody @Valid Mission rectif) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.missionService.modifierMission(rectif.getId(), rectif.getDateDebut(),
				rectif.getDateFin(), rectif.getVilleDepart(), rectif.getVilleArrivee(), rectif.getNature(), rectif.getTransport(), rectif.getCollegue(), rectif.getFicheDeFrais()));

	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<String> erreurdate(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
