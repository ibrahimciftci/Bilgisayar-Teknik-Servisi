package com.bilgeadam.BilgisayarTeknikServisi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilgeadam.BilgisayarTeknikServisi.model.Product;
import com.bilgeadam.BilgisayarTeknikServisi.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product findByName(String name) {
		return productRepository.findByName(name);
	}
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> getAll() {
		List<Product> list = productRepository.findAll();
		return list;
	}

}