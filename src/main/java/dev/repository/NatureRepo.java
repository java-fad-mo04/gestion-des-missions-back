package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	
	@Query("SELECT n FROM Nature n WHERE n.dateFin = null")
	List<Nature> listeLastNature() ;
	
}

