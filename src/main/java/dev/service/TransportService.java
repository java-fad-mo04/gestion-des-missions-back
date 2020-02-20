package dev.service;

import java.util.List;

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
}
