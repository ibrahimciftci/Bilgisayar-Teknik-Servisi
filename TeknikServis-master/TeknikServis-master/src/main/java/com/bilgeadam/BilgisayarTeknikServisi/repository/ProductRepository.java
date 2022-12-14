package com.bilgeadam.BilgisayarTeknikServisi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bilgeadam.BilgisayarTeknikServisi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	public Product findByName(String name);

}
