package dev.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Mission;

/**
 * Repository for the Mission
 * 
 * @author janka
 *
 */
public interface MissionRepo extends JpaRepository<Mission, Long> {

	Optional<Mission> findById(Long id);
	Optional<Mission> getDateDebut(LocalDate dateDebut);
	Optional<Mission> getDateFin(LocalDate dateFin);
	

}
