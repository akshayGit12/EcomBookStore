package com.nareshit.service;

import java.util.List;

import com.nareshit.entity.Rating;
import com.nareshit.model.RatingDto;

public interface RatingService {

	public Rating createRatingReview(RatingDto ratingDto);
	
	public List<Rating> getByAllReviews();
	
	public Rating getReview(Long id);
}
