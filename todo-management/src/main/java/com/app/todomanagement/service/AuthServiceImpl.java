package com.app.todomanagement.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.todomanagement.dto.LoginDto;
import com.app.todomanagement.dto.RegisterDto;
import com.app.todomanagement.entity.Role;
import com.app.todomanagement.entity.User;
import com.app.todomanagement.exception.TodoAPIException;
import com.app.todomanagement.repository.RoleRepository;
import com.app.todomanagement.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public String register(RegisterDto registerDto) {
		// check username is already exist in database
		if (userRepository.existsByUserName(registerDto.getUserName())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username is already exist...");

		}
		// check email is already exist in database
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email is already exist...");
		}

		User user = new User();
		user.setName(registerDto.getName());
		user.setUserName(registerDto.getUserName());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);

		user.setRoles(roles);

		userRepository.save(user);

		return "User Registered Successfully!.";
	}

	@Override
	public String login(LoginDto loginDto) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "User logged in successfully";
	}

}
