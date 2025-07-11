package com.user.mapper;

import com.user.dto.UserDTO;
import com.user.entity.User;

public class UserMapping {
 
	 public static UserDTO mapToUserDTO(User user , UserDTO userDTO) {
		 userDTO.setEmail(user.getEmail());
		 userDTO.setPassword(user.getPassword());
		 userDTO.setUserName(user.getUserName());
		 userDTO.setRole(user.getRole());
		return userDTO;
		 
	 }
	 
	 public static User mapToUser(UserDTO userDTO, User user) {
		 user.setEmail(userDTO.getEmail());
		 user.setPassword(userDTO.getPassword());
		 user.setUserName(userDTO.getUserName());
		 user.setRole(userDTO.getRole());
		 return user;
	 }
	 
}
