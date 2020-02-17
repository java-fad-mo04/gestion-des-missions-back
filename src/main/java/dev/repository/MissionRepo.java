package dev.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Collegue;
import dev.domain.Mission;

/**
 * Repository for the Mission
 * 
 * @author janka
 *
 */
public interface MissionRepo extends JpaRepository<Mission, Long> {

	@Query("SELECT m FROM Mission m, Collegue c WHERE m.collegue.id=c.id AND c.id=:id")
	List<Mission> findByCollegueId(Long id);

	@Query("SELECT m FROM Mission m WHERE m.collegue.id=:id AND m.dateDebut=:dateDebut AND m.dateFin=:dateFin")
	Optional<Collegue> findByCollegueIdDateDebutDateFin(Long id, LocalDate dateDebut, LocalDate dateFin);

}
