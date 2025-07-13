package com.user.repo;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User , Long> {
	
	Optional<User> findByEmail(String email);
	Optional<User> findByUserName(String userName);
	@Query("SELECT u FROM User u WHERE u.email = :input OR u.userName = :input")
	Optional<User> findByEmailOrUserName(@Param("input") String input);
}
