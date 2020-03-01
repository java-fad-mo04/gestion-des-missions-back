package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Override
	Optional<LigneDeFrais> findById(Long id);

	List<LigneDeFrais> findByMission(Mission mission);

	void save(FraisVM fraisIn);

	@Modifying
	@Query("DELETE FROM LigneDeFrais l WHERE l.mission.id=:id")
	void deleteByMissionId(@Param(value = "id") Long id);
}
