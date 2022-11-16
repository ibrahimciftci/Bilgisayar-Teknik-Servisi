
package com.bilgeadam.BilgisayarTeknikServisi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.BilgisayarTeknikServisi.model.Product;
import com.bilgeadam.BilgisayarTeknikServisi.model.Sale;

@Repository
@Transactional
public interface SaleRepository extends JpaRepository<Sale, Long> {

	public List<Sale> findByProduct(Product product);

	public List<Sale> findByFiyat(Integer fiyat);

	public List<Sale> findByproduct_id(Long id);
	
	public List<Sale> findByProduct_name(String name);

}
