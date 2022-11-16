package com.bilgeadam.BilgisayarTeknikServisi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
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
public class Proposal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private SystemUser user;

	@ManyToOne 
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;
	@Column
	private Integer fiyat;
	@Column
	private String note;

	@Column
	private Boolean durum = false;

	public Proposal(SystemUser user, Product product, Integer fiyat, String note, Boolean durum) {
		this.user = user;
		this.product = product;
		this.fiyat = fiyat;
		this.note = note;
		this.durum = durum;

	}
}