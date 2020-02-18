package dev.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import dev.domain.Nature;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.domain.Collegue;
import dev.domain.LigneDeFrais;
import dev.domain.Mission;
import dev.domain.Status;
import dev.domain.Transport;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.NatureRepo;

@Service
public class MissionService {

	private MissionRepo missionRepository;
	private CollegueRepo collegueRepository;
	private NatureRepo natureRepository;

	public MissionService(MissionRepo missionRepository) {
		super();
		this.missionRepository = missionRepository;
	}

	public ResponseEntity<?> modifierMission(Mission mission)
			throws Exception {

		// Récupérer la mission à modifier dans la liste.
		Optional<Mission> mymission = missionRepository.findById(mission.getId());

		// Créer les modifications de paramètres.
		if (!mymission.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mission inconnue");
		}
		Mission missModif = mymission.get();
		LocalDate d = missModif.getDateDebut();
		LocalDate f = missModif.getDateFin();

		// Vérifier qu'une mission n'en chevauche pas une autre.
		List<Mission> repertoire = missionRepository.findAll();
		for (Mission m : repertoire) {
			LocalDate debut = m.getDateDebut();
			LocalDate fin = m.getDateFin();

			// La date de fin de la mission à modifier doit être antérieure à la date de début d'une autre
			// mission (appelée m ici).
			// La date de début de la mission à modifier doit être postérieure à la date de fin d'une autre
			// mission (appelée m ici).
			if (!m.equals(mymission) && (f.isBefore(debut)|| d.isAfter(fin))) {
				throw new Exception("Deux missions ne peuvent avoir lieu en simultané");
			}

		}

		// Vérifier la cohérence de la date de début par rapport à la date de fin.
		if (mission.getDateDebut().isAfter(mission.getDateFin())) {
			throw new Exception("La date de début doit être antérieure à la date de fin");
		}

		// Vérifier la marge de 7 jours pour la réservation d'un avion
		else if (mission.getTransport().equals("avion") && LocalDate.now().compareTo(mission.getDateDebut()) < 8) {
			throw new Exception("Le transport par avion doit être réservé au moins 7 jours à l'avance");
		}

		// Vérifier que la mission ne débute pas le jour de sa déclaration.
		else if (mission.getDateDebut().compareTo(LocalDate.now()) <= 0 || mission.getDateFin().compareTo(LocalDate.now()) <= 0) {
			throw new Exception("La date de début et la date de fin doivent être postérieures à la date actuelle");
		} else {
			//Modifier la date.
			missModif.setDateDebut(mission.getDateDebut());
			missModif.setDateFin(mission.getDateFin());
		}

		// Modifier le collègue.
		missModif.setCollegue(mission.getCollegue());

		// Modifier les villes
		missModif.setVilleDepart(mission.getVilleDepart());
		missModif.setVilleArrivee(mission.getVilleArrivee());

		// Modifier la nature de la mission.
		missModif.setNature(mission.getNature());

		// Modifier le type de transport.
		missModif.setTransport(mission.getTransport());

		// Modifier la ligne de frais.
		missModif.setFicheDeFrais(mission.getFicheDeFrais());

		// Modifier le Statut.
		missModif.setStatus(Status.INITIALE);

		missionRepository.save(missModif);
		return ResponseEntity.status(HttpStatus.OK).body("La mission a bien été modifiée");
	}
}
