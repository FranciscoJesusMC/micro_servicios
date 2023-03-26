package com.review.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.review.service.entity.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {
	
	public List<Review> findByUserId(String userId);
	
	public List<Review> findByGameId(String gameId);
	
}
