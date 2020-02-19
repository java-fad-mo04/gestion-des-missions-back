package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.domain.LigneDeFrais;

/**
 * Repository for the class LigneDeFrais
 * 
 * @author janka
 *
 */
@Repository
public interface LigneDeFraisRepo extends JpaRepository<LigneDeFrais, Long> {

}
