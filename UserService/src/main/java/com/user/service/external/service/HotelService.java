package com.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.service.entites.Hotel;

@FeignClient(name= "HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("hotels/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId);

}
