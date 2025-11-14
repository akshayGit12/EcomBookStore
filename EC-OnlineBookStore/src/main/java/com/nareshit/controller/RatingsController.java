package com.nareshit.controller;

import org.springframework.web.bind.annotation.RestController;

import com.nareshit.entity.Rating;
import com.nareshit.model.RatingDto;
import com.nareshit.model.ResponseMessage;
import com.nareshit.repository.CartModuleRepo;
import com.nareshit.repository.FileRepo;
import com.nareshit.service.RatingService;
import com.nareshit.utility.Constants;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class RatingsController {

    private final FileRepo fileRepo;

    private final FileController fileController;

    private final CartModuleRepo cartModuleRepo;

	@Autowired RatingService ratingService;

    RatingsController(CartModuleRepo cartModuleRepo, FileController fileController, FileRepo fileRepo) {
        this.cartModuleRepo = cartModuleRepo;
        this.fileController = fileController;
        this.fileRepo = fileRepo;
    }
	
	@PostMapping("/rateBook")
	public ResponseEntity<ResponseMessage> rateBook(@RequestBody RatingDto ratingDto) {
		
		try {
			Rating ratingReview = ratingService.createRatingReview(ratingDto);
			if (ratingReview != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Thank you for sharing your feedback !"));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Failed to receive your feedback !"));
			}
		} catch (Exception e) {
		}
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILURE, "Internal server Error"));
	}
	
	// Getting all reviews 
	@GetMapping("/getAllReviews")
	public ResponseEntity<ResponseMessage> getAllReviews() {

		try {
			List<Rating> byAllReviews = ratingService.getByAllReviews();
			if (byAllReviews != null ) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Getting all reviews successfully"));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Getting reviews failed"));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILURE, "Internal Server Error !"));
		}
	}
	
	// gettoingn single user review
	@GetMapping("/getReview/{id}")
	public ResponseEntity<ResponseMessage> getReview(@PathVariable Long id) {
		
		Rating review = ratingService.getReview(id);
		if (review != null) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Gitting review successfully "));
		} else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Failed to get review"));
		}
	}
	
}
