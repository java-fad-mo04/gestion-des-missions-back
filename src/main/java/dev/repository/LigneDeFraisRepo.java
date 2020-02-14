package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.LigneDeFrais;

/**
 * Repository for the class LigneDeFrais
 * 
 * @author janka
 *
 */
public interface LigneDeFraisRepo extends JpaRepository<LigneDeFrais, Long> {

}
