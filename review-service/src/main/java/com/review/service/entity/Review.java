package com.review.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(value = "review")
public class Review {

	@Id
	private String reviewId;
	private String userId;
	private String gameId;
	private int rating;
	private String feedback;
}
