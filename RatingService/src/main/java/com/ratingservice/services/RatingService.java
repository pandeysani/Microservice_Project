package com.ratingservice.services;

import java.util.List;

import com.ratingservice.entites.Rating;

public interface RatingService {
	
	Rating create (Rating rating);
	
	List<Rating> getRating();
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
	

}
