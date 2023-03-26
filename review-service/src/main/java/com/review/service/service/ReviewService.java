package com.review.service.service;

import java.util.List;

import com.review.service.entity.Review;

public interface ReviewService {

	public Review createReview(Review review);
	
	public List<Review> getAllReviews();
	
	public List<Review> getAllReviewsByUserId(String userId);
	
	public List<Review> getALlReviewsByGameId(String gameId);
}
