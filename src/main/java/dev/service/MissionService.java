package dev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.domain.Mission;
import dev.domain.MissionDTO;
import dev.domain.Status;
import dev.repository.CollegueRepo;
import dev.repository.LigneDeFraisRepo;
import dev.repository.MissionRepo;
import dev.repository.NatureRepo;
import dev.repository.TransportRepo;
import dev.utils.DateChecker;

@Service
public class MissionService {
	private static final Supplier EntityExistsException = null;
	private MissionRepo missionRepo;
	private TransportRepo transportRepo;
	private CollegueRepo collegueRepo;

	private NatureRepo natureRepo;
	private LigneDeFraisRepo ligneDeFraisRepo;

	/**
	 * @param missionRepo
	 * @param transportRepo
	 * @param collegueRepo
	 * @param natureRepo
	 * @param ligneDeFraisRepo
	 */
	public MissionService(MissionRepo missionRepo, TransportRepo transportRepo, CollegueRepo collegueRepo,
			NatureRepo natureRepo, LigneDeFraisRepo ligneDeFraisRepo) {
		super();
		this.missionRepo = missionRepo;
		this.transportRepo = transportRepo;
		this.collegueRepo = collegueRepo;
		this.natureRepo = natureRepo;
		this.ligneDeFraisRepo = ligneDeFraisRepo;
	}

	/**
	 * @param missionIn
	 *            mission form input by user
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> createMission(MissionDTO missionIn) {
		// we do not check if collegue exists because s/he is logged in =>
		// Exists in the db!
		if (!this.missionRepo
				.findByCollegueIdDateDebutDateFin(missionIn.getCollegueId(), missionIn.getDateDebut(),
						missionIn.getDateFin())
				.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission pour ces dates pour ce collegue existe déjà.");
		}

		if (missionIn.getDateDebut().isAfter(missionIn.getDateFin())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La date de debut de mission ne peut pas être avant la date du fin.");
		}

		if (DateChecker.isToday(missionIn.getDateDebut())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission ne peut pas commencer ou finir aujourd'hui.");
		}
		
		if (DateChecker.isHoliday(missionIn.getDateDebut(), missionIn.getDateFin())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission ne peut pas commencer ou finir les jours fériés.");
		}

		if (this.transportRepo.findById(missionIn.getTransportId()).get().getLibelle().contains("Avion")
				&& !missionIn.getDateDebut().minusDays(7).isAfter(LocalDate.now())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission par avion ne peut commencer que 7 jours à compter d'aujourd'hui.");
		}

		//collegue has at least one mission in the db - check if the new overlaps with existing and a holiday
		if (!this.missionRepo.findByCollegueId(missionIn.getCollegueId()).isEmpty()) {
			List<Mission> missions = this.missionRepo.findByCollegueId(missionIn.getCollegueId());

			for (Mission m : missions) {
				if (!missionIn.getDateDebut().isAfter(m.getDateFin())
						&& !missionIn.getDateFin().isBefore(m.getDateDebut())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Une mission ne peut pas chevaucher avec une autre mission.");
				}
			}
		}
		Mission mission = new Mission();
		mission.setDateDebut(missionIn.getDateDebut());
		mission.setDateFin(missionIn.getDateFin());
		mission.setTransport(this.transportRepo.findById(missionIn.getTransportId())
				.orElseThrow(() -> new EntityExistsException("Transport avec cet id n'existe pas.")));
		mission.setNature(this.natureRepo.findById(missionIn.getNatureId())
				.orElseThrow(() -> new EntityExistsException("Nature avec cet id n'existe pas.")));
		mission.setCollegue(this.collegueRepo.findById(missionIn.getCollegueId())
				.orElseThrow(() -> new EntityExistsException("Collegue avec cet id n'existe pas.")));
		mission.setStatus(Status.INITIALE);
		this.missionRepo.save(mission);
		return ResponseEntity.status(HttpStatus.CREATED).body("La mission a bien été enregistrée.");
		}


	/**
	 * @return list of missions from database
	 */
	public List<MissionDTO> listMission() {
		List<Mission> missions = this.missionRepo.findAll();
		List<MissionDTO> missionsDto = new ArrayList<MissionDTO>();

		for (Mission m : missions) {
			MissionDTO md = new MissionDTO();
			md.setId(m.getId());
			md.setDateDebut(m.getDateDebut());
			md.setDateFin(m.getDateFin());
			md.setCollegueId(m.getCollegue().getId());
			md.setNatureId(m.getNature().getId());
			md.setStatus(m.getStatus());
			md.setTransportId(m.getTransport().getId());
			md.setVilleArrivee(m.getVilleArrivee());
			md.setVilleDepart(m.getVilleDepart());
			missionsDto.add(md);
		}
		return missionsDto;
	}

	public ResponseEntity<String> modifierMission(MissionDTO mission) throws Exception {

		// Récupérer la mission à modifier dans la liste.
		Optional<Mission> missionDb = missionRepo.findById(mission.getId());

		// Créer les modifications de paramètres.
		if (!missionDb.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mission inconnue");
		}
		Mission missModif = missionDb.get();
		LocalDate d = missModif.getDateDebut();
		LocalDate f = missModif.getDateFin();

		// Vérifier qu'une mission n'en chevauche pas une autre.
		List<Mission> repertoire = missionRepo.findAll();
		for (Mission m : repertoire) {
			LocalDate debut = m.getDateDebut();
			LocalDate fin = m.getDateFin();

			// La date de fin de la mission à modifier doit être antérieure à la
			// date de début d'une autre mission.
			// La date de début de la mission à modifier doit être postérieure à
			// la date de fin d'une autre mission .
			if (!m.equals(missionDb) && (f.isBefore(debut) || d.isAfter(fin))) {
				throw new Exception("Deux missions ne peuvent avoir lieu en simultané");
			}

		}

		// Vérifier la cohérence de la date de début par rapport à la date de fin.
		if (mission.getDateDebut().isAfter(mission.getDateFin())) {
			throw new Exception("La date de début doit être antérieure à la date de fin");
		}

		if (DateChecker.isHoliday(mission.getDateDebut(), mission.getDateFin())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission ne peut pas commencer ou finir les jours fériés.");
		}

		// Vérifier la marge de 7 jours pour la réservation d'un avion
		if (this.transportRepo.findById(mission.getTransportId()).get().getLibelle().contains("Avion")
				&& !mission.getDateDebut().minusDays(7).isAfter(LocalDate.now())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le transport par avion doit être réservé au moins 7 jours à l'avance");
		}

		// Vérifier que la mission ne débute pas le jour de sa déclaration.
		else if (mission.getDateDebut().compareTo(LocalDate.now()) <= 0
				|| mission.getDateFin().compareTo(LocalDate.now()) <= 0) {
			throw new Exception("La date de début et la date de fin doivent être postérieures à la date actuelle");
		} else {
			// Modifier la date.
			missModif.setDateDebut(mission.getDateDebut());
			missModif.setDateFin(mission.getDateFin());
		}

		// Modifier le collègue.
		missModif.setCollegue(this.collegueRepo.findById(mission.getCollegueId()).orElseThrow(()->new EntityExistsException("Collegue n'existe pas")));

		// Modifier les villes
		missModif.setVilleDepart(mission.getVilleDepart());
		missModif.setVilleArrivee(mission.getVilleArrivee());

		// Modifier la nature de la mission.
		missModif.setNature(this.natureRepo.findById(mission.getNatureId())
				.orElseThrow(() -> new EntityExistsException("Nature avec cet id n'existe pas.")));

		// Modifier le type de transport.
		missModif.setTransport(this.transportRepo.findById(mission.getTransportId())
				.orElseThrow(() -> new EntityExistsException("Transport avec cet id n'existe pas.")));


		// Modifier le Statut.
		missModif.setStatus(Status.INITIALE);

		// missModif.setFicheDeFrais(mission.getFicheDeFrais());

		this.missionRepo.save(missModif);
		return ResponseEntity.status(HttpStatus.OK).body("La mission a bien été modifiée");
	}
}
