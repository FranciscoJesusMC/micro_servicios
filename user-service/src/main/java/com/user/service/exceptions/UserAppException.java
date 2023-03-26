package com.user.service.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAppException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
}
