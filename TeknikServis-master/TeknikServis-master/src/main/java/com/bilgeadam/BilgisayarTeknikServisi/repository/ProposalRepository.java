package com.bilgeadam.BilgisayarTeknikServisi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bilgeadam.BilgisayarTeknikServisi.model.Proposal;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long>{
	public List<Proposal> findAllByUserId(Long id);

}