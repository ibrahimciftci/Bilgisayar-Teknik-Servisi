package com.bilgeadam.BilgisayarTeknikServisi.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bilgeadam.BilgisayarTeknikServisi.model.Role;
import com.bilgeadam.BilgisayarTeknikServisi.model.Sale;
import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;
import com.bilgeadam.BilgisayarTeknikServisi.service.SaleService;
import com.bilgeadam.BilgisayarTeknikServisi.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/component")
@AllArgsConstructor
public class RestApiController {

	private SaleService saleService;

	private UserService userService;
	
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/ram", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sale>> getRam(@RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password) {
		SystemUser user = userService.findByUsername(username);
		if(user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword() )) {

			return ResponseEntity.ok(saleService.getRamList("RAM"));

		} else {
			System.err.println("lütfen giriş yap");
			return null;
		}
	}

	@GetMapping(value = "/cpu", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sale>> getCpu(@RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password) {
		SystemUser user = userService.findByUsername(username);
		if(user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword() )) {

			return ResponseEntity.ok(saleService.getCpuList("CPU"));

		} else {
			System.err.println("lütfen giriş yap");
			return null;
		}
	}

	@GetMapping(value = "/gpu", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sale>> getGpu(@RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password) {
		SystemUser user = userService.findByUsername(username);
		if(user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword() )) {
			return ResponseEntity.ok(saleService.getGpuList("GPU"));

		}

		else {
			System.err.println("lütfen giriş yap");
			return null;
		}
	}

	@GetMapping(value = "/motherboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sale>> getMotherboard(@RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password) {
		SystemUser user = userService.findByUsername(username);
		if(user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword() )) {

			return ResponseEntity.ok(saleService.getMotherboardList("Motherboard"));

		} else {
			System.err.println("lütfen giriş yap");
			return null;
		}
	}

}