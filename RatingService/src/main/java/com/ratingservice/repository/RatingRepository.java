package com.ratingservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ratingservice.entites.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

	List<Rating> findByUserId (String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
