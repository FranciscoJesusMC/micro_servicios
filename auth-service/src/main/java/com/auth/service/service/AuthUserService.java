package com.auth.service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.service.dto.AuthUserDTO;
import com.auth.service.dto.JwtResponse;
import com.auth.service.entity.AuthUser;
import com.auth.service.repository.AuthUserRepository;
import com.auth.service.security.JwtProvider;

@Service
public class AuthUserService {
	
	@Autowired
	private AuthUserRepository authUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	

	public AuthUser create(AuthUserDTO dto) {
		Optional<AuthUser> user = authUserRepository.findByUsername(dto.getUsername());
		if(user.isPresent()) {
			return null;
		}
		
		String password = passwordEncoder.encode(dto.getPassword());
		AuthUser authUser = AuthUser.builder()
				.username(dto.getUsername())
				.password(password)
				.build();
		
		return authUserRepository.save(authUser);
	}
	
	public JwtResponse login(AuthUserDTO dto) {
		Optional<AuthUser> user = authUserRepository.findByUsername(dto.getUsername());
		if(!user.isPresent()) {
			return null;
		}
		
		if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
			return new JwtResponse(jwtProvider.generarToken(user.get()));
		}
		return null;
	}

	public JwtResponse validate(String token) {
		if(!jwtProvider.validate(token)) {
			return null;
		}
		
		String username= jwtProvider.getUserNameFromToken(token);
		if(!authUserRepository.findByUsername(username).isPresent()) {
			return null;
		}
		
		return new JwtResponse(token);
	}
}
