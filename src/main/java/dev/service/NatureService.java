package dev.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

	public List<Nature> listeLastNature() {
		return this.natureRepository.listeLastNature();
	}
	
	/**Ajout d'une nature 
	 * 
	 * Param : Nature
	 * Return ResponseEntity<String>
	 * */

	public ResponseEntity<String> ajoutNature(Nature nature) {

		if (this.natureRepository.existsByLibelle(nature.getLibelle().trim().toUpperCase())) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nature deja existante");

		}

			this.natureRepository.save(new Nature(nature.getLibelle().trim().toUpperCase(), nature.isEstFacture(), nature.isEstPrime(),
					nature.getTjm(), nature.getValeurPrime()));

		

		return ResponseEntity.status(HttpStatus.CREATED).body("Nature enregistré");

	}
	/** Modification d'une nature , Sauvegarde l'ancienne avec une date j-1 a la modification
	 * Param String libelle, Nature nature
	 * 
	 * return ResponseEntity<String>
	*/
	
	public ResponseEntity<String> modifierNature(Nature nature){
		
		Optional<Nature> recupNature = this.natureRepository.findById(nature.getId());
		
		
		if(!recupNature.isPresent()){			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nature non trouvé");
		}
		
		Nature modifNature = recupNature.get();
		
		if(modifNature.isEstFacture()== nature.isEstFacture() && modifNature.isEstPrime() == nature.isEstPrime() && modifNature.getTjm() == nature.getTjm() && modifNature.getValeurPrime() == nature.getValeurPrime()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nature identique non modifiée");
		}
		
		
		modifNature.setDateFin(LocalDate.now().minusDays(1));
		
		this.natureRepository.save(modifNature);
		
		this.natureRepository.save(new Nature(nature.getLibelle(),nature.isEstFacture(),nature.isEstPrime(),nature.getTjm(),nature.getValeurPrime()));
		
		
		return ResponseEntity.status(HttpStatus.OK).body("Nature modifié");
		
	}
	
	

}