package com.bilgeadam.BilgisayarTeknikServisi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bilgeadam.BilgisayarTeknikServisi.model.SystemUser;

@Transactional
public interface UserRepository extends JpaRepository<SystemUser, Long> {

	public SystemUser findByUsername(String name);

	@Query(value = "SELECT code	FROM SystemUser su where su.username=:userName")
	public String getVerifyCode(@Param("userName") String userName);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE SystemUser su	SET  su.code=NULL WHERE   su.username=:username")
	public void deleteVerifyCode(@Param("username") String username);
}
