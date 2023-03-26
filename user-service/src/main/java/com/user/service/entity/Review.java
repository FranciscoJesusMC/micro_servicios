package com.user.service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Review {

	private String reviewId;
	private String userId;
	private String gameId;
	private int rating;
	private String feedback;
	private Game game;
}
