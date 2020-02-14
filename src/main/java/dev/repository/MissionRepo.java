package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Mission;

/**
 * Repository for the Mission
 * 
 * @author janka
 *
 */
public interface MissionRepo extends JpaRepository<Mission, Long> {

}
