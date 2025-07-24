package com.user.service;

import java.util.List;

import com.user.dto.UserDTO;

public interface IUserService {
	void registerUser(UserDTO userDTO);
	void registerAdmin(UserDTO userDTO);
	
	List<UserDTO> showAllUser();
}
