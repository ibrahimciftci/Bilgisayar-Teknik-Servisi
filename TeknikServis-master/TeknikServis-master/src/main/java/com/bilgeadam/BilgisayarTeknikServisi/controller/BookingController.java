package com.bilgeadam.BilgisayarTeknikServisi.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.BilgisayarTeknikServisi.model.Booking;
import com.bilgeadam.BilgisayarTeknikServisi.model.Service;
import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;
import com.bilgeadam.BilgisayarTeknikServisi.service.BookingService;
import com.bilgeadam.BilgisayarTeknikServisi.service.ServiceService;
import com.bilgeadam.BilgisayarTeknikServisi.service.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookingController {
	@NonNull
	private ServiceService serviceService;
	@NonNull
	private UserService userService;
	@NonNull
	private BookingService bookingService;

	@GetMapping(path = "/booking")
	public ModelAndView booking(Authentication auth) {
		ModelAndView loginView = new ModelAndView("booking");

		Date input;

		if (bookingService.getDate() != null) {
			input = new Date();
			LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			loginView.addObject("date", date);
		} else {
			input = new Date();
			LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			loginView.addObject("date", date);
		}

		Booking booking = new Booking();
		SystemUser systemUser = userService.findByUsername(auth.getName());
		loginView.addObject("bookinglist", bookingService.findByUserId(systemUser.getId()));
		loginView.addObject("booking", booking);
		loginView.addObject("systemUser", systemUser);
		loginView.addObject("services", serviceService.findAll());

		return loginView;

	}

	@GetMapping(path = "/confirmsave")
	public ModelAndView booking(Booking booking, HttpServletRequest request) {
		Date date = bookingService.getNextDate(booking, 10);
		request.getSession().setAttribute("bd", date);

		ModelAndView loginView = new ModelAndView("confirmsave");
		Date input = booking.getZaman();

		booking.setZaman(date);

		loginView.addObject("booking", booking);

		return loginView;

	}

	@PostMapping(path = "/confirmsave")
	public ModelAndView bookingPost(Booking booking, Service service, Authentication auth, HttpServletRequest request) {
		Date dtf = (Date) request.getSession().getAttribute("bd");
		booking.setUser(userService.findByUsername(auth.getName()));
		booking.setZaman(dtf);
		bookingService.save(booking);
		return new ModelAndView("redirect:/index");

	}

	@GetMapping(path = "/booking/delete/{id}")
	public ModelAndView bookingdelete(@PathVariable(value = "id") Long id, Authentication auth) {
		SystemUser systemUser = userService.findByUsername(auth.getName());
		Booking bookingDatabase = bookingService.findById(id);

		SystemUser systemUserDatabase = bookingDatabase.getUser();
		Long databaseId = systemUserDatabase.getId();

		if (systemUser.getId() == databaseId) {
			bookingService.deleteById(id);
		} else {
			return new ModelAndView("redirect:/index");
		}

		return new ModelAndView("redirect:/booking");

	}

	@GetMapping(path = "/bookinglist")
	public ModelAndView getAll() {
		ModelAndView bookingView = new ModelAndView("bookinglist");
		bookingView.addObject("liste", bookingService.getAll());
		return bookingView;
	}

	@PostMapping(path = "/bookinglist/sil")
	// public ModelAndView sil(@ModelAttribute(name = "OGR_ID") Long asdasdas)
	public ModelAndView sil(Long id) {
		bookingService.deleteById(id);
		return new ModelAndView("redirect:/bookinglist");
	}

}
