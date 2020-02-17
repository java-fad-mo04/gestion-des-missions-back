package dev.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.domain.Nature;
import dev.repository.NatureRepo;

/** Service de l'entite nature
 * 
 * 
 * */


@Service
public class NatureService {

	private NatureRepo natureRepository;

	public NatureService(NatureRepo natureRepo) {

		this.natureRepository = natureRepo;
	}
	
	/**Recuperation de la liste des natures
	 * 
	 * */

	public List<Nature> listeNature() {
		return this.natureRepository.findAll();
	}
	
	/**Ajout d'une nature 
	 * 
	 * Param : Nature
	 * Return ResponseEntity<String>
	 * */

	public ResponseEntity<String> ajoutNature(Nature nature) {

		if (this.natureRepository.existsByLibelle(nature.getLibelle().toUpperCase().trim())) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nature deja existante");

		}

			this.natureRepository.save(new Nature(nature.getLibelle().trim(), nature.isEstFacture(), nature.isEstPrime(),
					nature.getTjm(), nature.getValeurPrime()));

		

		return ResponseEntity.status(HttpStatus.CREATED).body("Nature enregistré");

	}
	
	public ResponseEntity<String> modifierNature(String libelle,Nature nature){
		
		
		
		return ResponseEntity.status(HttpStatus.OK).body("Nature modifié");
		
	}
	
	

}
