package com.bilgeadam.BilgisayarTeknikServisi.service;


import java.util.List;

import com.bilgeadam.BilgisayarTeknikServisi.model.Basket;
import com.bilgeadam.BilgisayarTeknikServisi.model.Sale;
import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;
import com.bilgeadam.BilgisayarTeknikServisi.repository.BasketRepository;
import com.bilgeadam.BilgisayarTeknikServisi.repository.SaleRepository;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class BasketService {

	private SaleRepository saleRepo;
	private BasketRepository repo;
	
	public List<Basket> getBasketByUserId(Long id)
	{
		return repo.findAllBasketByuser_id(id);
	}
	
	public Basket insertBasket(Long saleId,SystemUser user)
	{
		
		return repo.save(new Basket(saleRepo.findById(saleId).get(),user));
	}

	public void deleteByuser_id(Long id) {
		repo.deleteByuser_id(id);
		
	}
	
}
