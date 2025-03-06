package com.wipro.jwtsecurity.service;

import com.wipro.jwtsecurity.entity.UserEntity;

public interface AuthService {
	UserEntity register(UserEntity userEntity);
	
	String login(String usernameOrEmail, String password);

}
