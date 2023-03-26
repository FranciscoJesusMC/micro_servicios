package com.review.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.review.service.entity.Review;
import com.review.service.repository.ReviewRepository;
import com.review.service.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public Review createReview(Review review) {
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	@Override
	public List<Review> getAllReviewsByUserId(String userId) {
		return reviewRepository.findByUserId(userId);
	}

	@Override
	public List<Review> getALlReviewsByGameId(String gameId) {
		return reviewRepository.findByGameId(gameId);
	}
}
