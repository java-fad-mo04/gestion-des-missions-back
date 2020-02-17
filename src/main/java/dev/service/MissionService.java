package dev.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import dev.domain.Nature;
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

	public Mission modifierMission(Long id, LocalDate dateDebut, LocalDate dateFin, String villeDepart,
			String villeArrivee, Nature nature, Transport transport, Collegue collegue, LigneDeFrais ficheDeFrais)
			throws Exception {

		// Récupérer la mission à modifier dans la liste.
		Optional<Mission> mymission = missionRepository.findById(id);

		// Créer les modifications de paramètres.
		if (!mymission.isPresent()) {
			throw new EntityNotFoundException("Mission inconnue");
		}
		Mission missModif = mymission.get();
		LocalDate d = missModif.getDateDebut();
		LocalDate f = missModif.getDateFin();

		// Vérifier qu'une mission n'en chevauche pas une autre.
		List<Mission> repertoire = missionRepository.findAll();
		for (Mission m : repertoire) {
			LocalDate debut = m.getDateDebut();
			LocalDate fin = m.getDateFin();

			// La fin de la mission à modifier doit être antérieure au début d'une autre
			// mission (appelée m ici).
			// Le début de la mission à modifier doit être postérieur à la fin d'une autre
			// mission (appelée m ici).
			if (!m.equals(mymission) && (f.compareTo(debut) >= 0 || d.compareTo(fin) <= 0)) {
				throw new Exception("Deux missions ne peuvent avoir lieu en simultané");
			}

		}

		// Vérifier la cohérence de la date de début par rapport à la date de fin.
		if (dateDebut.isAfter(dateFin)) {
			throw new Exception("La date de début doit être antérieure à la date de fin");
		}

		// Vérifier la marge de 7 jours pour la réservation d'un avion
		else if (transport.equals("avion") && LocalDate.now().compareTo(dateDebut) < 8) {
			throw new Exception("Le transport par avion doit être réservé au moins 7 jours à l'avance");
		}

		// Vérifier que la mission ne débute pas le jour de sa déclaration.
		else if (dateDebut.compareTo(LocalDate.now()) <= 0 || dateFin.compareTo(LocalDate.now()) <= 0) {
			throw new Exception("La date de début et la date de fin doivent être postérieures à la date actuelle");
		} else {
			//Modifier la date.
			missModif.setDateDebut(dateDebut);
			missModif.setDateFin(dateFin);
		}

		// Modifier le collègue.
		missModif.setCollegue(collegue);

		// Modifier les villes
		missModif.setVilleDepart(villeDepart);
		missModif.setVilleArrivee(villeArrivee);

		// Modifier la nature de la mission.
		missModif.setNature(nature);

		// Modifier le type de transport.
		missModif.setTransport(transport);

		// Modifier la ligne de frais.
		missModif.setFicheDeFrais(ficheDeFrais);

		// Modifier le Statut.
		missModif.setStatus(Status.INITIALE);

		return missionRepository.save(missModif);
	}
}
