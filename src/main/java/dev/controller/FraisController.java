/**
 * 
 */
package dev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.FraisVM;
import dev.service.FraisService;

/**
 * @author clementlittardi
 *
 */

@RestController
@RequestMapping("frais")
public class FraisController {

	private FraisService fraisService;
	
	public FraisController(FraisService fraisService) {
		this.fraisService = fraisService;
	}
	
	@GetMapping
	public List<FraisVM> listFrais() {
		return this.fraisService.listFrais();
	}
	
	@PostMapping
	public ResponseEntity<String> createFrais(@RequestBody @Valid FraisVM fraisIn) {
		return this.fraisService.createFrais(fraisIn);
	}

	@PatchMapping
	public ResponseEntity<String> patchReservation(@RequestBody @Valid FraisVM fraisIn) throws Exception {
		return this.fraisService.modifierFrais(fraisIn);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteFrais(@RequestBody @Valid Long id) {
		return this.fraisService.supprimerFrais(id);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<String> erreurdate(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
