package com.bilgeadam.BilgisayarTeknikServisi.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if (exception.getClass().equals(BadCredentialsException.class)) {
			response.sendRedirect("/systemlogin?e=1");
		} else if (exception.getClass().equals(InternalAuthenticationServiceException.class)) {
			response.sendRedirect("/systemlogin?e=2");
		} else {
			response.sendRedirect("/systemlogin?e=3");
		}
		System.err.println(exception.getClass());
		// TODO Auto-generated method stub

	}

}
