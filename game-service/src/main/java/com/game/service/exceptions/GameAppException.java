package com.game.service.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameAppException  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;


}
