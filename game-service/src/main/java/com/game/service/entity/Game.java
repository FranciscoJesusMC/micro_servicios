package com.game.service.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {

	@Id
	@Column(name = "id")
	private String gameId;
	private String name;
	private String description;
	private String platform;
	private BigDecimal price;
}
