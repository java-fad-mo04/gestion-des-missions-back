
package dev.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.controller.vm.CollegueVM;
import dev.controller.vm.MissionVM;
import dev.domain.Mission;
import dev.domain.RoleCollegue;
import dev.domain.Status;
import dev.repository.CollegueRepo;
import dev.repository.LigneDeFraisRepo;
import dev.repository.MissionRepo;
import dev.repository.NatureRepo;
import dev.repository.TransportRepo;
import dev.utils.DateChecker;

@Service
public class MissionService {
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
	public ResponseEntity<String> createMission(MissionVM missionIn) {

		if (!this.missionRepo
				.findByCollegueIdDateDebutDateFin(missionIn.getCollegue().getId(), missionIn.getDateDebut(),
						missionIn.getDateFin())
				.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission existe déjà pour ce collègue à ces dates");
		}

		if (missionIn.getDateDebut().isAfter(missionIn.getDateFin())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La date de début de mission doit être antérieure à la date de fin");
		}

		if (DateChecker.isToday(missionIn.getDateDebut())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission ne peut pas commencer ou finir à la date d'aujourd'hui");
		}
		
		if (DateChecker.isHoliday(missionIn.getDateDebut(), missionIn.getDateFin())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission ne peut pas commencer ou finir un jour férié");
		}

		if (this.transportRepo.findById(missionIn.getTransport().getId()).get().getLibelle().contains("Avion")
				&& !missionIn.getDateDebut().minusDays(7).isAfter(LocalDate.now())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le transport par avion doit être réservé au moins 7 jours à l'avance");
		}

		// collegue has at least one mission in the db - check if the new
		// overlaps with existing
		if (!this.missionRepo.findByCollegueId(missionIn.getCollegue().getId()).isEmpty()) {
			List<Mission> missions = this.missionRepo.findByCollegueId(missionIn.getCollegue().getId());


			for (Mission m : missions) {
				System.out.println(missionIn.getDateDebut().isAfter(m.getDateFin()));
				if (!missionIn.getDateDebut().isAfter(m.getDateFin())
						&& !missionIn.getDateFin().isBefore(m.getDateDebut())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Deux missions ne peuvent avoir lieu en simultané");
				}
			}
		}

		Mission mission = new Mission();
		mission.setDateDebut(missionIn.getDateDebut());
		mission.setDateFin(missionIn.getDateFin());
		mission.setTransport(this.transportRepo.findById(missionIn.getTransport().getId())
				.orElseThrow(() -> new EntityExistsException("Aucun transport n'existe pour cet id")));
		mission.setNature(this.natureRepo.findById(missionIn.getNature().getId())
				.orElseThrow(() -> new EntityExistsException("Aucune nature n'existe pour cet id")));
		mission.setCollegue(this.collegueRepo.findById(missionIn.getCollegue().getId())
				.orElseThrow(() -> new EntityExistsException("Aucun collègue n'existe pour cet id")));
		mission.setStatus(Status.INITIALE);
		mission.setVilleDepart(missionIn.getVilleDepart());
		mission.setVilleArrivee(missionIn.getVilleArrivee());
		this.missionRepo.save(mission);
		return ResponseEntity.status(HttpStatus.CREATED).body("La mission a bien été enregistrée");
		}


	/**
	 * @return list of missions from database
	 */
	public List<MissionVM> listMission() {
		return this.missionRepo.findAll().stream().map(MissionVM::new).collect(Collectors.toList());
	}

	public MissionVM recupMission(Long id) throws Exception {
		MissionVM md = new MissionVM();
		Mission m = missionRepo.findById(id).orElseThrow(() -> new Exception("La mission n'existe pas"));
		CollegueVM coll = new CollegueVM();
		coll.setId(m.getCollegue().getId());
		coll.setEmail(m.getCollegue().getEmail());
		coll.setNom(m.getCollegue().getNom());
		coll.setPrenom(m.getCollegue().getPrenom());
		for (RoleCollegue role : m.getCollegue().getRoles()) {
			coll.getRoles().add(role.getRole());
		}
		
		md.setId(m.getId());
		md.setDateDebut(m.getDateDebut());
		md.setDateFin(m.getDateFin());
		md.setCollegue(coll);
		md.setNature(m.getNature());
		md.setStatus(m.getStatus());
		md.setTransport(m.getTransport());
		md.setVilleArrivee(m.getVilleArrivee());
		md.setVilleDepart(m.getVilleDepart());
		return md;
	}
	/**
	 * @param mission
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<String> modifierMission(MissionVM mission) throws Exception {

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
			if (!m.equals(missionDb) && (f.isBefore(debut) && d.isAfter(fin))) {
				throw new Exception("Deux missions ne peuvent avoir lieu en simultané");
			}

		}

		// Vérifier la cohérence de la date de début par rapport à la date de fin.
		if (mission.getDateDebut().isAfter(mission.getDateFin())) {
			throw new Exception("La date de début doit être antérieure à la date de fin");
		}

		if (mission.getDateDebut().equals(mission.getDateFin())) {
			throw new Exception("La date de début doit être différente de la date de fin");
		}
		if (DateChecker.isHoliday(mission.getDateDebut(), mission.getDateFin())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission ne peut pas commencer ou finir un jour férié");
		}

		// Vérifier la marge de 7 jours pour la réservation d'un avion
		if (this.transportRepo.findById(mission.getTransport().getId()).get().getLibelle().contains("Avion")
				&& !mission.getDateDebut().minusDays(7).isAfter(LocalDate.now())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le transport par avion doit être réservé au moins 7 jours à l'avance");
		}

		// Vérifier que la mission ne débute pas le jour de sa déclaration.
		else if (mission.getDateDebut().compareTo(LocalDate.now()) <= 0) {
			throw new Exception("La date de début et la date de fin doivent être postérieures à la date actuelle");
		} else {
			// Modifier la date.
			missModif.setDateDebut(mission.getDateDebut());
			missModif.setDateFin(mission.getDateFin());
		}

		// Modifier le collègue.
		missModif.setCollegue(this.collegueRepo.findById(mission.getCollegue().getId()).orElseThrow(()->new EntityExistsException("Ce collègue n'existe pas")));

		// Modifier les villes
		missModif.setVilleDepart(mission.getVilleDepart());
		missModif.setVilleArrivee(mission.getVilleArrivee());

		// Modifier la nature de la mission.
		missModif.setNature(this.natureRepo.findById(mission.getNature().getId())
				.orElseThrow(() -> new EntityExistsException("Aucune nature n'existe pour cet id")));

		// Modifier le type de transport.
		missModif.setTransport(this.transportRepo.findById(mission.getTransport().getId())
				.orElseThrow(() -> new EntityExistsException("Aucun transport n'existe pour cet id")));


		// Modifier le Statut.
		missModif.setStatus(Status.INITIALE);

		// missModif.setFicheDeFrais(mission.getFicheDeFrais());

		this.missionRepo.save(missModif);
		return ResponseEntity.status(HttpStatus.OK).body("La mission a bien été modifiée");
	}
}
