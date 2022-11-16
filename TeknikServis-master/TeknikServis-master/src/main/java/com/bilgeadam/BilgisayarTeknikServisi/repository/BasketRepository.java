package com.bilgeadam.BilgisayarTeknikServisi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.BilgisayarTeknikServisi.model.Basket;

@Transactional
public interface BasketRepository extends JpaRepository<Basket, Long> {

	List<Basket> findAllBasketByuser_id(Long id);

	void deleteByuser_id(Long id);

	
}
