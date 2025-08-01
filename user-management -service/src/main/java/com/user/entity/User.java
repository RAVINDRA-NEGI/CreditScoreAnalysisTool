package com.user.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@NoArgsConstructor@AllArgsConstructor @ToString
public class User {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false , unique = true)
	private String userName;
	
	@Column(nullable = false , unique = true)
	private String email;
	
	@Column(nullable = false , unique = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "create_dt")
	@JsonIgnore
	private Date createdAt;
	
	public enum Role{
		USER,
		ADMIN
	}

}
