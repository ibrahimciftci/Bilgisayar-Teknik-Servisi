package com.bilgeadam.BilgisayarTeknikServisi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.BilgisayarTeknikServisi.constraint.SaleValidator;
import com.bilgeadam.BilgisayarTeknikServisi.model.Sale;
import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;
import com.bilgeadam.BilgisayarTeknikServisi.service.BasketService;
import com.bilgeadam.BilgisayarTeknikServisi.service.ProductService;
import com.bilgeadam.BilgisayarTeknikServisi.service.SaleService;
import com.bilgeadam.BilgisayarTeknikServisi.service.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("sale")
@RequiredArgsConstructor
public class SaleController {

	@NonNull
	private SaleService saleService;

	@NonNull
	private UserService userService;

	@NonNull
	private BasketService basketService;

	@NonNull
	private ProductService productService;

	@NonNull
	private SaleValidator saleValidator;

	@Value("${image.location}")
	public String image_location;

	@RequestMapping(value = "/getSaleByType", method = RequestMethod.GET)
	public ModelAndView getProductByType(@RequestParam(name = "type") Long type) {
		ModelAndView productView = new ModelAndView("sales");
		productView.addObject("liste", saleService.getByProduct(type));
		return productView;
	}

	@GetMapping(path = "list")
	public ModelAndView getAll() {
		ModelAndView productView = new ModelAndView("sales");
		productView.addObject("liste", saleService.getAll());
		return productView;
	}

	@GetMapping(path = "odeme")
	public ModelAndView odemeYap(Authentication auth) {
		SystemUser systemUser = userService.findByUsername(auth.getName());
		ModelAndView productView = new ModelAndView("odeme");
		basketService.deleteByuser_id(systemUser.getId());
		return productView;
	}

	@GetMapping(path = "/sellproduct")
	public ModelAndView sell() {

		ModelAndView saleView = new ModelAndView("sellproduct");
		saleView.addObject("products", productService.getAll());
		saleView.addObject("sale", new Sale());
		saleView.addObject("saleslist", saleService.getAll());
		return saleView;
	}

	@PostMapping("/sellproduct")
	public ModelAndView sellingPost(@ModelAttribute(name = "sale") @Validated Sale sale,
			@RequestParam("productImage") MultipartFile file) {

		saleService.save(sale);
		try {
			Files.write(Paths.get(image_location + sale.getId() + ".jpg"), file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/sale/sellproduct");
	}

	@PostMapping(path = "/deleteProduct")
	public ModelAndView deleteProduct(Long id) {

		saleService.deleteById(id);
		return new ModelAndView("redirect:/sale/sellproduct");
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(saleValidator);
	}

	@ExceptionHandler(value = BindException.class)
	public ModelAndView exception(Exception ex, Errors errors) {
		ModelAndView saleView = new ModelAndView("sellproduct");
		saleView.addObject("products", productService.getAll());
		saleView.addObject("sale", new Sale());
		saleView.addObject("saleslist", saleService.getAll());
		for (ObjectError error : errors.getAllErrors()) {
			saleView.addObject(error.getCode(), 1);

		}
		return saleView;
	}
}
