package com.bilgeadam.BilgisayarTeknikServisi.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class ErrController implements ErrorController {
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Exception e) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error-404";
			}
//			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//				
//				return "error-500";
//			}
		}
		return "error";
	}

	@ExceptionHandler
	public ModelAndView exception(Exception ex) {
		ModelAndView saleView = new ModelAndView("error-500");
		System.err.println("buraya düşüldü 3");
		return saleView;
	}

	@ExceptionHandler
	public ModelAndView exception(ArithmeticException ex) {
		ModelAndView saleView = new ModelAndView("error-500");
		System.err.println("buraya düşüldü");
		return saleView;
	}

	@ExceptionHandler
	public ModelAndView exception(IndexOutOfBoundsException ex) {
		ModelAndView saleView = new ModelAndView("error-500");
		System.err.println(ex.getMessage());
		return saleView;
	}

}
