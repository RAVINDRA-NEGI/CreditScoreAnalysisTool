package com.user.service;

import java.util.List;

import com.user.dto.UsersDTO;

public interface IUserService {
	void registerUser(UsersDTO userDTO);
	void registerAdmin(UsersDTO userDTO);
	
	List<UsersDTO> showAllUser();
}
