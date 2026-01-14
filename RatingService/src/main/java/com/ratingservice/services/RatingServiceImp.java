package com.ratingservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingservice.entites.Rating;
import com.ratingservice.repository.RatingRepository;

@Service
public class RatingServiceImp implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating create(Rating rating) {
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRating() {
	
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
	
		return ratingRepository.findByUserId(hotelId);
	}

}
