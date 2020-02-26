/**
 * 
 */
package dev.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.controller.vm.FraisVM;
import dev.domain.LigneDeFrais;
import dev.repository.LigneDeFraisRepo;
import dev.repository.MissionRepo;

/**
 * @author clementlittardi
 *
 */
@Service
public class FraisService {

	/** Attributs */
	private LigneDeFraisRepo ligneDeFraisRepo;
	private MissionRepo missionRepo;

	/** Constructeur */
	public FraisService(LigneDeFraisRepo ligneDeFraisRepo, MissionRepo missionRepo) {
		super();
		this.ligneDeFraisRepo = ligneDeFraisRepo;
		this.missionRepo = missionRepo;
	}


	public ResponseEntity<String> createFrais(FraisVM fraisIn) {
		if (montantPositif(fraisIn.getMontant()) && verificationDate(fraisIn.getMission().getDateDebut(),
				fraisIn.getMission().getDateFin(), fraisIn.getDate())
				&& verificationUnique(ligneDeFraisRepo.findAll(), fraisIn)) {
			ligneDeFraisRepo.save(fraisIn);
			return ResponseEntity.status(HttpStatus.CREATED).body("La ligne de frais a bien été enregistrée.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une ligne de frais avec ces informations existe déjà.");
		}
	}

	public List<FraisVM> listFrais() {
		return this.ligneDeFraisRepo.findAll().stream().map(FraisVM::new).collect(Collectors.toList());
	}

	public ResponseEntity<String> modifierFrais(FraisVM fraisIn) throws Exception {

		// Récupérer la ligne de frais à modifier dans la liste.
		Optional<LigneDeFrais> fraisDb = ligneDeFraisRepo.findById(fraisIn.getId());

		// Créer les modifications de paramètres.
		if (montantPositif(fraisIn.getMontant()) && verificationDate(fraisIn.getMission().getDateDebut(),
				fraisIn.getMission().getDateFin(), fraisIn.getDate())
				&& verificationUnique(ligneDeFraisRepo.findAll(), fraisIn)) {
			
			LigneDeFrais fraisModif = fraisDb.get();

			// Modifier la date.
			fraisModif.setDate(fraisIn.getDate());
			
			//Modifier le montant
			fraisModif.setMontant(fraisIn.getMontant());
			
			//Modifier la nature du frais
			fraisModif.setLibelle(fraisIn.getLibelle());
			
			//Modifier la mission
			//fraisModif.setMission(fraisIn.getMission());
			fraisModif.setMission(this.missionRepo.findById(fraisIn.getMission().getId())
					.orElseThrow(() -> new EntityExistsException("Mission avec cet id n'existe pas.")));
			
			ligneDeFraisRepo.save(fraisIn);
			return ResponseEntity.status(HttpStatus.OK).body("La ligne de frais a bien été modifiée");
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erreur de modification de la ligne de frais.");
			
		}
	}
	
	public ResponseEntity<String> supprimerFrais(Long id) {
		ligneDeFraisRepo.deleteById(id);
		// ligneDeFraisRepo.save(fraisIn);
		return ResponseEntity.status(HttpStatus.OK).body("La ligne de frais a bien été supprimée");
	}
	

	/** Règles métier */
	public boolean montantPositif(BigDecimal bigDecimal) {
		return bigDecimal.compareTo(BigDecimal.ZERO) > 0;
	}

	public boolean verificationDate(LocalDate dateDebut, LocalDate dateFin, LocalDate date) {
		return (date.isAfter(dateDebut) && date.isBefore(dateFin));
	}

	public boolean verificationUnique(List<LigneDeFrais> liste, FraisVM fraisIn) {
		for (LigneDeFrais l : liste) {
			if (l.getDate().isEqual(fraisIn.getDate()) && l.getLibelle().equals(fraisIn.getLibelle())) {
				return false;
			}
		}
		return true;
	}
}
