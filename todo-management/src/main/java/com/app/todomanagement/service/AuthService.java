package com.app.todomanagement.service;

import com.app.todomanagement.dto.LoginDto;
import com.app.todomanagement.dto.RegisterDto;

public interface AuthService {
	String register(RegisterDto registerDto);
	
	String login(LoginDto loginDto);
}
