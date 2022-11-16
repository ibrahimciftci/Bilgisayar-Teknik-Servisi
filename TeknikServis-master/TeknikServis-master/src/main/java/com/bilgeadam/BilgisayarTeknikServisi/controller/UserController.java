package com.bilgeadam.BilgisayarTeknikServisi.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.BilgisayarTeknikServisi.constraint.UserValidator;
import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;
import com.bilgeadam.BilgisayarTeknikServisi.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {

	UserService userService;
	UserValidator userValidator;

	@GetMapping("/index")
	public String viewHome() {
		return "index";
	}

	// handler method to handle user registration form request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		// create model object to store form data
		SystemUser systemUser = new SystemUser();
		model.addAttribute("systemUser", systemUser);
		return "register";
	}

	@PostMapping("/register/save")
	public String saveRegistration(@ModelAttribute("systemUser") @Validated SystemUser systemUser, BindingResult result,
			Model model) {

		SystemUser existingUser = userService.findByUsername(systemUser.getUsername());

		if (existingUser != null && existingUser.getUsername() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("username", null, "Bu kullanıcı adı zaten kullanılmaktadır.");
		}

		if (result.hasErrors()) {
			model.addAttribute("systemUser", systemUser);
			return "/register";
		}

		userService.save(systemUser);
		return "redirect:/register?success";

	}

	@GetMapping(path = "/systemlogin")
	public ModelAndView login(HttpServletRequest request) {
		if (request.getUserPrincipal() != null) {
			return new ModelAndView("redirect:/index");
		}
		ModelAndView loginView = new ModelAndView("systemlogin");
		SystemUser systemUser = new SystemUser();

//		loginView.addObject("verify", systemUserService.getVerifyCode());
		loginView.addObject("user", systemUser);

		return loginView;
	}

	@GetMapping(path = "/loginerror")
	public ModelAndView loginError() {

		ModelAndView loginView = new ModelAndView("systemlogin");
		String loginerror = "Lütfen Kullanıcı Adı ve Şifrenizi Kontrol Ediniz";

		loginView.addObject("loginerror", loginerror);

		return loginView;
	}

	@PostMapping(path = "/systemlogin")
	public ModelAndView loginPost(SystemUser systemUser, HttpServletRequest request) {
		if (userService.getVerifyCode(systemUser.getUsername()) != null) {
			return new ModelAndView("redirect:/systemlogin?c=1");
		} else {
			try {
				request.login(systemUser.getUsername(), systemUser.getPassword());
			} catch (ServletException e) {
				return new ModelAndView("redirect:/systemlogin?c=3");
			}
			return new ModelAndView("redirect:/index");
		}

	}

	@GetMapping(path = "/verify")
	public ModelAndView verify() {
		SystemUser systemUser = new SystemUser();
		ModelAndView loginView = new ModelAndView("verify");
		loginView.addObject("user", systemUser);
		return loginView;

	}

	@PostMapping(path = "/verify")
	public ModelAndView verify(SystemUser systemUser, HttpServletRequest request) {
		String verifyCode = userService.getVerifyCode(systemUser.getUsername());
		if (verifyCode == null) {
			return new ModelAndView("redirect:/systemlogin?c=2");
		}
		if (verifyCode.equals(systemUser.getCode())) {
			try {
				request.login(systemUser.getUsername(), systemUser.getPassword());
				userService.deleteVerifyCode(systemUser.getUsername());
			} catch (ServletException e) {
				e.printStackTrace();
			}
			return new ModelAndView("redirect:/index");
		} else {

			return new ModelAndView("redirect:/verify?c=1");
		}

	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@ExceptionHandler(value = BindException.class)
	public Model exception(Exception ex, Errors errors, Model model) {
		SystemUser systemUser = new SystemUser();
		model.addAttribute("systemUser", systemUser);
		for (ObjectError error : errors.getAllErrors()) {
			model.addAttribute(error.getCode(), 1);

		}
		return model;
	}

}
