package com.user.service.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Game {

	private String gameId;
	private String name;
	private String description;
	private String platform;
	private BigDecimal price;
}
