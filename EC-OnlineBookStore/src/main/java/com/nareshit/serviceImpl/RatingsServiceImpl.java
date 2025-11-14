package com.nareshit.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nareshit.entity.BooksModule;
import com.nareshit.entity.Customer;
import com.nareshit.entity.Rating;
import com.nareshit.exceptions.BookIdNotFoundException;
import com.nareshit.exceptions.CustmerIDNotFoundException;
import com.nareshit.model.RatingDto;
import com.nareshit.repository.BooksModuleRepo;
import com.nareshit.repository.CustmerRepoo;
import com.nareshit.repository.RatingRepo;
import com.nareshit.service.RatingService;

public class RatingsServiceImpl implements RatingService{

	@Autowired CustmerRepoo custmerRepoo;
	@Autowired BooksModuleRepo booksModuleRepo;
	@Autowired RatingRepo ratingRepo;
	@Override
	public Rating createRatingReview(RatingDto ratingDto) {
		
		// check custmer is exits in DB
		Customer customer = custmerRepoo.findById(ratingDto.getCustmerId()).orElseThrow(()-> new CustmerIDNotFoundException("Customer id not found !"));
		// check Book is exits in DB
		BooksModule booksModule = booksModuleRepo.findById(ratingDto.getBookId()).orElseThrow(()->new BookIdNotFoundException("Book id not found !"));
		
		Rating rate = new Rating();
		rate.setBooksModule(booksModule);
		rate.setCustomer(customer);
		rate.setRate(ratingDto.getRate());
		rate.setReviewText(ratingDto.getReviewText());
		ratingRepo.save(rate);
		return rate;
	}
	
	@Override
	public List<Rating> getByAllReviews() {	
		return ratingRepo.findAll();
	}

	@Override
	public Rating getReview(Long id) {
		Optional<Rating> byId = ratingRepo.findById(id);
		return byId.get();
	}
//	
//	@Override
//	public Customer getByCustmersId(Long id) {
//		Optional<Customer> byId = custmerRepoo.findById(id);
//		if(!byId.isPresent()) {
//			
//			throw new CustmerIDNotFoundException("Custmer Id Not Found");
//		}
//		return byId.get();
//	}

	
	
	
}
