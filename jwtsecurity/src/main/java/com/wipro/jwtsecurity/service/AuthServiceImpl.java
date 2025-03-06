package com.wipro.jwtsecurity.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.jwtsecurity.entity.RoleEntity;
import com.wipro.jwtsecurity.entity.UserEntity;
import com.wipro.jwtsecurity.repository.RoleRepository;
import com.wipro.jwtsecurity.repository.UserRepository;
import com.wipro.jwtsecurity.util.JwtTokenUtil;
@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserEntity register(UserEntity userEntity) {
		
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		Set<RoleEntity> userRoles = new HashSet<>();
		Set<RoleEntity> roles = userEntity.getRoles();
		roles.forEach(r -> {
			Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(r.getId());
			if (optionalRoleEntity.isEmpty()) {
				
			}
			RoleEntity roleEntity = optionalRoleEntity.get();
			userRoles.add(roleEntity);
		}); 
		userEntity.setRoles(userRoles);
		
		userRepository.save(userEntity);
		return userEntity;
	}

	@Override
	public String login(String usernameOrEmail, String password) {
		//logic to validate user and password
		//if login is success,generate jwt token
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenUtil.generateToken(authentication);
		
		
		return token;
	}

}
