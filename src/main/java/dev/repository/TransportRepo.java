package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Transport;

/**
 * Repository for the class Transport
 * 
 * @author janka
 *
 */
public interface TransportRepo extends JpaRepository<Transport, Long> {

	Transport findByLibelle(String libelle);
}
