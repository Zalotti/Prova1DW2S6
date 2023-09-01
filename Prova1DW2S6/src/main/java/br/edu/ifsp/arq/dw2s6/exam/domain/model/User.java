package br.edu.ifsp.arq.dw2s6.exam.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 @NotNull
	 @Size(min = 3, max = 50)
     private String name;
	 @NotNull
	 @Email
     private String email;
	 @NotNull
     private String password;
     @NotNull
     private Boolean active; 
     
	 public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean isActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
}
