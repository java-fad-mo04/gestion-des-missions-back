package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Collegue;

/**
 * Repository for the Collegue
 * 
 * @author janka
 *
 */
public interface CollegueRepo extends JpaRepository<Collegue, Long> {

    Optional<Collegue> findByEmail(String email);
}
