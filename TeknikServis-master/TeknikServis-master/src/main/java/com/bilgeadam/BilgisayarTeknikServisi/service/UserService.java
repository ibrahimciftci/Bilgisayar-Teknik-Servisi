package com.bilgeadam.BilgisayarTeknikServisi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bilgeadam.BilgisayarTeknikServisi.model.Role;
import com.bilgeadam.BilgisayarTeknikServisi.model.Service;
import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;
import com.bilgeadam.BilgisayarTeknikServisi.repository.RoleRepository;
import com.bilgeadam.BilgisayarTeknikServisi.repository.ServiceRepository;
import com.bilgeadam.BilgisayarTeknikServisi.repository.UserRepository;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	UserRepository repo;

	RoleRepository roleRepo;

	PasswordEncoder passwordEncoder;

	ServiceRepository serviceRepository;

	public boolean save(SystemUser systemUser) {
		systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
		Role role = roleRepo.findByName("ROLE_USER");

		systemUser.setRoles(Arrays.asList(role));

		// login verification code
		Random rand = new Random();
		String code = Integer.toString(rand.nextInt(1000));
		systemUser.setCode(code);

		return repo.save(systemUser) != null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SystemUser systemUser;
		systemUser = repo.findByUsername(username);
		UserBuilder builder = User.withUsername(username);
		//
		builder.password(systemUser.getPassword());
		builder.authorities(systemUser.getRoles());
		return builder.build();
	}

	public SystemUser findByUsername(String username) {

		return repo.findByUsername(username);
	}

	public String getVerifyCode(String userName) {
		return repo.getVerifyCode(userName);
	}

	public void deleteVerifyCode(String verifyCode) {
		repo.deleteVerifyCode(verifyCode);
	}

	public List<Service> findAll() {
		return serviceRepository.findAll();
	}

}
