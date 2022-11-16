package com.bilgeadam.BilgisayarTeknikServisi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.BilgisayarTeknikServisi.model.Service;

@Transactional
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
