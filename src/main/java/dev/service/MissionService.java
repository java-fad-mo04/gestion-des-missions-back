package dev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

	public List<MissionDTO> listMission() {
		List<Mission> missions = this.missionRepo.findAll();
		List<MissionDTO> missionsDto = new ArrayList<MissionDTO>();

		for (Mission m : missions) {
			MissionDTO md = new MissionDTO();
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
}
