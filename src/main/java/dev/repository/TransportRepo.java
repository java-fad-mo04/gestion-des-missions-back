package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.domain.Transport;

/**
 * Repository for the class Transport
 * 
 * @author janka
 *
 */
@Repository
public interface TransportRepo extends JpaRepository<Transport, Long> {

}
