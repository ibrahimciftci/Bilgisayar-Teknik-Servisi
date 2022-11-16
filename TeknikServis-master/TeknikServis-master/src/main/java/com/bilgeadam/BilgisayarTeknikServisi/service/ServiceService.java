package com.bilgeadam.BilgisayarTeknikServisi.service;

import java.util.List;

import com.bilgeadam.BilgisayarTeknikServisi.model.Service;
import com.bilgeadam.BilgisayarTeknikServisi.repository.ServiceRepository;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceService {
	ServiceRepository serviceRepository;

	public List<Service> findAll() {
		return serviceRepository.findAll();
	}

}
