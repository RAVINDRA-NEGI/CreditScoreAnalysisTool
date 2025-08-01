package com.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.constants.Constants;
import com.user.dto.ResponseDTO;
import com.user.dto.UserDTO;
import com.user.service.IUserService;
import com.user.service.impl.MyUserDetailsService;
import com.user.util.JwtUtil;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private final IUserService iUserService;
	
	AuthenticationManager authenticationManager;
	
	private JwtUtil jwtUtil;
	
	MyUserDetailsService myUserDetailsService;
	
	@PostMapping("/userregister")
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO){
		iUserService.registerUser(userDTO);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDTO(Constants.STATUS_201 , Constants.MESSAGE_201));
	}
	@PostMapping("/adminregister")
	public ResponseEntity<ResponseDTO> registerAdmin(@RequestBody UserDTO userDTO){
		iUserService.registerAdmin(userDTO);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDTO(Constants.STATUS_201, Constants.MESSAGE_201));
	}
	
	@GetMapping("allusers")
	public ResponseEntity<List<UserDTO>> showAllUser(){
		List<UserDTO> users = iUserService.showAllUser();
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(users);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
		
		try {
			String input ;
			if(userDTO.getUserName() != null) {
				input = userDTO.getUserName();
			}else if(userDTO.getEmail() != null) {
				input = userDTO.getEmail();
				
			}else {
				return ResponseEntity.badRequest().body("Username or email must be provided");
			}

			String password = userDTO.getPassword();
			
			Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(input, password)
	        );
			if(authentication.isAuthenticated()) {
				UserDetails userDetails = myUserDetailsService.loadUserByUsername(input);

				String jwt = jwtUtil.generateToken(userDetails);
				return  ResponseEntity.ok(jwt);
			   } else {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		        }
		    } catch (BadCredentialsException e) {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                             .body("An internal error occurred");
		    }
		
	}
}

