package com.bilgeadam.BilgisayarTeknikServisi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.catalina.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sale
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
    
    @Column
    private Integer fiyat;
    
    @Column(length=300)
    private String note;
    
	public Sale(Product product, Integer fiyat, String note)
	{
		this.product = product;
		this.fiyat =fiyat;
		this.note = note;

	}
   
   



}
