package dev.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.domain.Mission;

/**
 * Repository for the Mission
 * 
 * @author janka
 *
 */
@Repository
public interface MissionRepo extends JpaRepository<Mission, Long> {

	@Query("SELECT m FROM Mission m WHERE m.id=:id")
	List<Mission> findByCollegueId(@Param(value = "id") Long id);

	@Query("SELECT m FROM Mission m WHERE m.collegue.id=:id AND m.dateDebut=:dateDebut AND m.dateFin=:dateFin")
	List<Mission> findByCollegueIdDateDebutDateFin(@Param(value = "id") Long id,
			@Param(value = "dateDebut") LocalDate dateDebut, @Param(value = "dateFin") LocalDate dateFin);
	
	
	List<Mission> findByNatureId(Long id);

}
