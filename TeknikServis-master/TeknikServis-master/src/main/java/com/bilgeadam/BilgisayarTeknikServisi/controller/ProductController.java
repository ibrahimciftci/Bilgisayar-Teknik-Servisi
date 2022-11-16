package com.bilgeadam.BilgisayarTeknikServisi.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.BilgisayarTeknikServisi.model.Product;
import com.bilgeadam.BilgisayarTeknikServisi.service.ProductService;
import com.bilgeadam.BilgisayarTeknikServisi.service.SaleService;


@Controller
@RequestMapping(path = "product")
public class ProductController {

	private ProductService productService;
	
	private SaleService saleService;
	
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}



	
	

	
	
}
