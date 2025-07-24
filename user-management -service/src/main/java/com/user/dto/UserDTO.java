package com.user.dto;

import com.user.entity.User.Role;

import lombok.Data;

@Data
public class UserDTO {
	
	private String userName;
	private String email;
	private String password;
	private Role role;
}
