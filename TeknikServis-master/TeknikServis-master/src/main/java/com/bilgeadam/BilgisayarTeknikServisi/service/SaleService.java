package com.bilgeadam.BilgisayarTeknikServisi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bilgeadam.BilgisayarTeknikServisi.model.Sale;
import com.bilgeadam.BilgisayarTeknikServisi.repository.SaleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleService {
	private SaleRepository saleRepository;

	public void save(Sale sale) {
		saleRepository.save(sale);
	}

	public Sale getById(Long id) {
		return saleRepository.findById(id).get();
	}

	public boolean deleteById(Long id) {

		saleRepository.deleteById(id);
		return true;
	}

	public List<Sale> getAll() {

		return saleRepository.findAll();
	}

	public Object getByProduct(Long type) {
		return saleRepository.findByproduct_id(type);
	}
	public List<Sale> getRamList(String name) {
		return saleRepository.findByProduct_name(name);
	}



	public List<Sale> getCpuList(String name) {
		
		return saleRepository.findByProduct_name(name);
	}



	public List<Sale> getGpuList(String name) {
		
		return saleRepository.findByProduct_name(name);
	}



	public List<Sale> getMotherboardList(String name) {
		
		return saleRepository.findByProduct_name(name);
	}

}
