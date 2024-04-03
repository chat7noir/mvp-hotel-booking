package com.ib.test.hrs.mvphotelbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ib.test.hrs.mvphotelbooking.model.Booking;
import com.ib.test.hrs.mvphotelbooking.model.Hotel;
import com.ib.test.hrs.mvphotelbooking.service.BookingService;
import com.ib.test.hrs.mvphotelbooking.service.HotelService;

/**
 * Main Booking controller providing REST API endpoints.
 * 
 * @author Igal BITAN
 */
@RestController
public class BookingController {

	// TODO - add authentication?

	@Autowired
	private HotelService hotelService;

	@Autowired
	private BookingService bookingService;

	/**
	 * Search for Hotel 1 - full city name
	 * "A user should be able to search for hotels in specific destination"
	 */
	@GetMapping("/hotels/destination/{cityName}")
	List<Hotel> hotelsByDestination(@PathVariable String cityName) {
		return this.hotelService.getAllByCityName(cityName);
	}

	/**
	 * Search for Hotel 2 - city name contains
	 * "A user should be able to search for hotels in specific destination"
	 */
	@GetMapping("/hotels/destination-search/{cityNameContaining}")
	List<Hotel> hotelsByDestinationContaining(@PathVariable String cityNameContaining) {
		return this.hotelService.getAllByCityNameContaining(cityNameContaining);
	}

	/*
	 * Create Hotel Booking
	 * "A user should be able book a hotel for a number of days"
	 */
	@PostMapping(value = "/bookings/new", consumes = {MediaType.APPLICATION_JSON_VALUE /*, MediaType.APPLICATION_FORM_URLENCODED_VALUE*/})
	Booking createBooking(@RequestBody Booking booking) {
		return this.bookingService.save(booking);
	}

	/**
	 * View Hotel Booking Details
	 * "A user should be able to see hotel booking details"
	 */
	@GetMapping("/bookings/{id}")
	Booking bookingById(@PathVariable long id) {
		return this.bookingService.getById(id);
	}

}
