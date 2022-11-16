package com.bilgeadam.BilgisayarTeknikServisi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilgeadam.BilgisayarTeknikServisi.model.Booking;
import com.bilgeadam.BilgisayarTeknikServisi.model.Proposal;
import com.bilgeadam.BilgisayarTeknikServisi.repository.ProposalRepository;

@Service
public class ProposalService {

	@Autowired
	ProposalRepository proposalRepository;

	public List<Proposal> findAllByUserId(Long userId) {
		return proposalRepository.findAllByUserId(userId);
	}

	public List<Proposal> findAll() {
		return proposalRepository.findAll();
	}

	public void deleteById(Long id) {
		proposalRepository.deleteById(id);
	}

	public Proposal findById(Long id) {
		return proposalRepository.findById(id).get();
	}

	public void save(Proposal proposal) {
		proposalRepository.save(proposal);
	}

}