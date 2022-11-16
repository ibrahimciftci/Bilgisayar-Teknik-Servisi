package com.bilgeadam.BilgisayarTeknikServisi.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bilgeadam.BilgisayarTeknikServisi.model.Booking;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query(value = "SELECT b.zaman FROM Booking b ORDER BY b.zaman DESC LIMIT 1", nativeQuery = true)
	public Date getDate();

	public List<Booking> findByZaman(Date date);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Booking  SET zaman=:zaman  WHERE  zaman= (SELECT zaman FROM Booking  ORDER BY zaman DESC LIMIT 1)", nativeQuery = true)
	public void updateDate(@Param("zaman") Date zaman);

	public List<Booking> findByUserId(Long id);
	
	public List<Booking> findAllByOrderByZamanAsc(); 

}
