package com.app.todomanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.todomanagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserName(String userName);
	
	Boolean existsByEmail(String email);

	Optional<User> findByNameOrEmail(String name, String email);
	
	Boolean existsByUserName(String userName);
}
