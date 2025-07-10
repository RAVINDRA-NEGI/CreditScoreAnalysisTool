package com.user.dto;

import com.user.entity.Users.Role;

import lombok.Data;

@Data
public class UsersDTO {
	
	private String userName;
	private String email;
	private String password;
	private Role role;
}
