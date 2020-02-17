package dev.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.domain.Mission;
import dev.domain.Status;
import dev.repository.MissionRepo;
import dev.utils.DateChecker;

public class MissionService {
	private MissionRepo missionRepo;


	/**
	 * @param missionRepo mission repository
	 */
	public MissionService(MissionRepo missionRepo) {
		super();
		this.missionRepo = missionRepo;

	}

	/**
	 * @param missionIn
	 *            mission form input by user
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> createMission(Mission missionIn) {
		// we do not check if collegue exists because s/he is logged in =>
		// Exists in the db!

		if (this.missionRepo.findByCollegueIdDateDebutDateFin(missionIn.getId(), missionIn.getDateDebut(),
				missionIn.getDateFin()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission pour ces dates pour ce collegue existe déjà.");
		}

		if (!DateChecker.isToday(missionIn.getDateDebut())
				&& !DateChecker.isHoliday(missionIn.getDateDebut(), missionIn.getDateFin())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission ne peut pas commencer ou finir aujourd'hui et les jours fériés.");
		}

		if (missionIn.getTransport().getLibelle().contains("Avion")
				&& !missionIn.getDateDebut().minusDays(7).isAfter(LocalDate.now())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Une mission par avion peut commencer ne que 7 jours à compter d'aujourd'hui.");
		}

		//collegue has at least one mission in the db - check if the new overlaps with existing and a holiday
		// check this
		if (!this.missionRepo.findByCollegueId(missionIn.getCollegue().getId()).isEmpty()) {
			List<Mission> missions = this.missionRepo.findByCollegueId(missionIn.getCollegue().getId());

			for (Mission m : missions) {
				if (!missionIn.getDateDebut().isAfter(m.getDateFin())
						&& !missionIn.getDateFin().isBefore(m.getDateDebut())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Une mission ne peut pas chevaucher avec une autre mission.");
				}
			}
		}
		// to check this part
		missionIn.setStatus(Status.INITIALE);
		this.missionRepo.save(missionIn);
		return ResponseEntity.status(HttpStatus.CREATED).body("La mission a bien été enregistrée.");
	}
}
