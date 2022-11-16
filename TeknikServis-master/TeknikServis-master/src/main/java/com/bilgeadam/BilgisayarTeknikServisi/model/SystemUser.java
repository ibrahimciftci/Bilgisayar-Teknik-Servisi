package com.bilgeadam.BilgisayarTeknikServisi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class SystemUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false, unique = true)
//	@NotEmpty(message = "{username.not.empty}")
//	@Size(max = 15, min = 5, message = "{username.size}")
	private String username = "";

	@Column(length = 50, nullable = false, unique = true)
//	@Email(message = "{email.format}", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
//	@NotEmpty(message = "{email.not.empty}")
	private String email = "";

	@Column(length = 150, nullable = false)
//	@NotEmpty(message = "{password.not.empty}")
	private String password = "";

	@Column()
	private String code = "";

	public SystemUser() {
	}

	public SystemUser(String username, String email, String password) {
		this.username = username;
		this.password = password;
		this.email = email;

	}

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();

}