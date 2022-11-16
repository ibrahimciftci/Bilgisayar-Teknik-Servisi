package com.bilgeadam.BilgisayarTeknikServisi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.BilgisayarTeknikServisi.model.Role;

@Transactional
public interface RoleRepository extends JpaRepository<Role, String> {

	public Role findByName(String name);
	
	

}
