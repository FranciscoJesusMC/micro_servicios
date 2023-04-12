package com.auth.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.dto.AuthUserDTO;
import com.auth.service.dto.JwtResponse;
import com.auth.service.entity.AuthUser;
import com.auth.service.service.AuthUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthUserController {

	@Autowired
	private AuthUserService authUserService;
	
	@GetMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthUserDTO authUserDTO){
		JwtResponse token = authUserService.login(authUserDTO);
		if(token == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(token);
	}

	@PostMapping("/validate")
	public ResponseEntity<JwtResponse> validate(@RequestParam String token){
		JwtResponse tokenDTO = authUserService.validate(token);
		if(token == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(tokenDTO);
	}
	
	@PostMapping("/create")
	public ResponseEntity<AuthUser> create(@RequestBody AuthUserDTO authUserDTO){
		AuthUser user = authUserService.create(authUserDTO);
		if(user == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(user);
	}
}
