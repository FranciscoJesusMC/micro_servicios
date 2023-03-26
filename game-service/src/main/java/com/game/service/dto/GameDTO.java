package com.game.service.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {

	@NotEmpty(message = "field cannot be empty")
	@Size(max = 30,message = "max 30 characters")
	private String name;
	@NotEmpty(message = "field cannot be empty")
	@Size(max = 100,message = "max 100 characters")
	private String description;
	@NotEmpty(message = "field cannot be empty")
	@Size(max = 10,message = "max 10 characters")
	private String platform;
	@DecimalMin(value = "0",inclusive = true)
	@DecimalMax(value = "100",inclusive = true)
	private BigDecimal price;
}
