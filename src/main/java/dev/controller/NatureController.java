package dev.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Nature;
import dev.service.NatureService;

/**Controller de l'entite nature
 * 
 * 
 * */

@CrossOrigin
@RestController
@RequestMapping("nature")
public class NatureController {

	private static final Logger LOG = LoggerFactory.getLogger(NatureController.class);

	private NatureService natureServ;

	public NatureController(NatureService natureServ) {
		this.natureServ = natureServ;
	}
	
	/**Recuperation de la liste des natures
	 * 
	 * */
	
	@GetMapping()
	public List<Nature>listeNature(){
		return natureServ.listeNature();
	}
	
	/**Ajout d'une nature via la methode POST
	 * 
	 * Return ResponseEntity<String>
	 * */
	@PostMapping()
	public ResponseEntity<String>ajoutNature(@RequestBody @Valid Nature nature){

		return natureServ.ajoutNature(nature);

	}
	
	
	@PatchMapping(path="/{libelleNature}")
	public ResponseEntity<String>modifierNature(@RequestBody @Valid Nature nature ,@PathVariable("libelleNature") String libelle){

		return natureServ.modifierNature(libelle,nature);

	}

	
	
}
