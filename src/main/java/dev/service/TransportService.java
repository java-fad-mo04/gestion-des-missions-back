package dev.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import dev.domain.Transport;
import dev.repository.TransportRepo;

@Service
public class TransportService {
	private TransportRepo transportRepo;

	/**Constructor
	 * @param transportRepo transport repository
	 */
	public TransportService(TransportRepo transportRepo) {
		this.transportRepo = transportRepo;
	}

	
	/**
	 * @return list of transport types
	 */
	public List<Transport> getTransport() {
		return this.transportRepo.findAll();
	}
	
	/** Get Transport by id
	 * @param id identification number
	 * @return object Transport corresponding to the id
	 */
	public Transport getById(Long id) {
		return this.transportRepo.findById(id)
				.orElseThrow(() -> new EntityExistsException("Transport avec cet id n'existe pas."));
	}
	
}
