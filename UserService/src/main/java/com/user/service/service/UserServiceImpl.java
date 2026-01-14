package com.user.service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entites.Hotel;
import com.user.service.entites.Rating;
import com.user.service.entites.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.service.HotelService;
import com.user.service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	@Override
	public User saveUser(User user) {

		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {

		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given idn is not found on server  !! :" + userId));
		// http://localhost:8083/ratings/users/2ba052d5-272c-453b-a079-febe735a9fda
		Rating[] ratingOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		logger.info("{}", ratingOfUser);
		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
			// http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
			
			
//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
			
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			//logger.info("response status code: {} ", forEntity.getStatusCode());
			// set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
