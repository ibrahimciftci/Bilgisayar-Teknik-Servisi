package com.bilgeadam.BilgisayarTeknikServisi.controller;

import java.util.List;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.BilgisayarTeknikServisi.model.Basket;
import com.bilgeadam.BilgisayarTeknikServisi.model.Sale;
import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;
import com.bilgeadam.BilgisayarTeknikServisi.repository.BasketRepository;
import com.bilgeadam.BilgisayarTeknikServisi.service.BasketService;
import com.bilgeadam.BilgisayarTeknikServisi.service.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping(path = "basket")
@RequiredArgsConstructor
public class BasketController {
	
	@NonNull
	private UserService userService;
	
	@NonNull
	private BasketService basketService;
	
	BasketRepository basketRepo;

	@GetMapping(path = "list")
	public ModelAndView getAll(Authentication auth)
	{
		SystemUser systemUser = userService.findByUsername(auth.getName());
		ModelAndView basketView = new ModelAndView("basket");
		
		List<Basket> list=basketService.getBasketByUserId(systemUser.getId());
		basketView.addObject("basket", list);
		return basketView;
	}
	
	@RequestMapping(value="/insertBasket", method=RequestMethod.GET)
	public ModelAndView insertBasket(Authentication auth,@RequestParam(name = "saleId") Long saleId)
	{
		SystemUser systemUser = userService.findByUsername(auth.getName());
		basketService.insertBasket(saleId,systemUser);
		return new ModelAndView("redirect:/basket/list");
	}
	
	
}
