package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.domain.Collegue;

/**
 * Repository for the Collegue
 * 
 * @author janka
 *
 */
@Repository
public interface CollegueRepo extends JpaRepository<Collegue, Long> {

    Optional<Collegue> findByEmail(String email);
}
