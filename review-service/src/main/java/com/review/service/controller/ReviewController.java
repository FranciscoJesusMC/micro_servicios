package com.review.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.review.service.entity.Review;
import com.review.service.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews(){
		List<Review> reviews = reviewService.getAllReviews();
		return ResponseEntity.ok(reviews);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable(name = "userId")String userId){
		List<Review> reviews = reviewService.getAllReviewsByUserId(userId);
		return ResponseEntity.ok(reviews);
	}
	
	@GetMapping("/game/{gameId}")
	public ResponseEntity<List<Review>> getReviewsByGameId(@PathVariable(name = "gameId")String gameId){
		List<Review> reviews = reviewService.getALlReviewsByGameId(gameId);
		return ResponseEntity.ok(reviews);
	}
	
	@PostMapping
	public ResponseEntity<Review> createReview (@RequestBody Review review){
		Review newReview = reviewService.createReview(review);
		return new ResponseEntity<>(newReview,HttpStatus.CREATED);
	}
}
