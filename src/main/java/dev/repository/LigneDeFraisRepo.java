package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.controller.vm.FraisVM;
import dev.domain.LigneDeFrais;
import dev.domain.Mission;

/**
 * Repository for the class LigneDeFrais
 * 
 * @author janka
 *
 */
@Repository
public interface LigneDeFraisRepo extends JpaRepository<LigneDeFrais, Long> {

	Optional<LigneDeFrais> findById(long id);
	List<LigneDeFrais> findByMission(Mission mission);
	void save(FraisVM fraisIn);
}
