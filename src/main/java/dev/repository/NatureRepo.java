package dev.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.domain.Nature;

/**
 * Repository for the Nature of the Mission
 * 
 * @author janka
 *
 */
public interface NatureRepo extends JpaRepository<Nature, Long> {
	
	boolean existsByLibelle(String libelle);

	Optional<Nature> findByLibelle(String libelle);
	
	boolean existsByLibelleAndDateFin(String libelle,LocalDate date);
	
	@Query("SELECT n FROM Nature n WHERE n.dateFin = null")
	List<Nature> listeLastNature() ;
	
	@Override
	@Modifying
	@Query("DELETE FROM Nature n WHERE n.id=:id")
	void deleteById(@Param(value = "id") Long id);
}

