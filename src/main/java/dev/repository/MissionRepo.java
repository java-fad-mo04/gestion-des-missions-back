package dev.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.domain.Mission;
import dev.domain.Nature;

/**
 * Repository for the Mission
 * 
 * @author janka
 *
 */
@Repository
public interface MissionRepo extends JpaRepository<Mission, Long> {

	@Query("SELECT m FROM Mission m WHERE m.collegue.id=:id")
	List<Mission> findByCollegueId(@Param(value = "id") Long id);

	@Query("SELECT m FROM Mission m WHERE m.collegue.id=:id AND m.dateDebut=:dateDebut AND m.dateFin=:dateFin")
	List<Mission> findByCollegueIdDateDebutDateFin(@Param(value = "id") Long id,
			@Param(value = "dateDebut") LocalDate dateDebut, @Param(value = "dateFin") LocalDate dateFin);

	@Override
	@Modifying
	@Query("DELETE FROM Mission m WHERE m.id=:id")
	void deleteById(@Param(value = "id") Long id);



	List<Nature> findByNatureId(Long idNature);

}

