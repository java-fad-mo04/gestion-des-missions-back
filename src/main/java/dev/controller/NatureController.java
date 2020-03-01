package dev.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Nature;
import dev.exception.NatureException;
import dev.service.NatureService;

/**
 * Controller de l'entite nature
 * 
 * 
 */

@CrossOrigin
@RestController
@RequestMapping("nature")
public class NatureController {

	private NatureService natureServ;

	public NatureController(NatureService natureServ) {
		this.natureServ = natureServ;
	}

	/**
	 * Recuperation de la liste des natures les plus recentes
	 * 
	 * */
	
	
	/**
	 * Ajout d'une nature via la methode POST
	 * */
	
	@GetMapping
	public List<Nature>listeLastNature(){
		return this.natureServ.listeLastNature();
	}
	
	/** Get nature by id
	 * @param id identification number
	 * @return object Nature corresponding to the id
	 */
	@GetMapping(params = "id")
	public Nature getById(@RequestParam("id") Long id) {
		return this.natureServ.getById(id);
	}

	/**Ajout d'une nature via la methode POST
>>>>>>> 3bcda3d5757403fea8832be262ea20db73fde107
	 * 
	 * Return ResponseEntity<String>
	 */
	@PostMapping()
	public ResponseEntity<String> ajoutNature(@RequestBody @Valid Nature nature) {

		return natureServ.ajoutNature(nature);

	}

	/**
	 * Modification d'une nature via la méthode PATCH
	 * 
	 */

	@PatchMapping()
	public ResponseEntity<String> modifierNature(@RequestBody @Valid Nature nature) {

		return natureServ.modifierNature(nature);

	}
	
	/**Supression d'un nature
	 * 
	
	 * */
	@DeleteMapping(path="/{id}")
	public ResponseEntity<String> deleteNature(@PathVariable("id") Long idNature) {

		return natureServ.deleteNature(idNature);

	}
	

	@ExceptionHandler
	public ResponseEntity<?> reponse(NatureException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

	}

}