package dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Transport;
import dev.service.TransportService;

@CrossOrigin
@RestController
@RequestMapping("transport")
public class TransportController {
	private TransportService transportService;

	/**
	 * Constructor
	 * 
	 * @param transportService
	 *            transportService
	 */
	public TransportController(TransportService transportService) {
		super();
		this.transportService = transportService;
	}


	/**
	 * @return list of transport types
	 */
	@GetMapping
	public List<Transport> getTransport() {
		return this.transportService.getTransport();
	}

}
