package com.ib.test.hrs.mvphotelbooking.service;

import java.util.List;

import com.ib.test.hrs.mvphotelbooking.model.Hotel;

/**
 * Service pour l'entité {@link Hotel}
 * 
 * @author Igal BITAN
 */
public interface HotelService {

	/** @return the hotel by its id */
	Hotel getById(long id);

	/** @return all the hotels for a specified city (name, case insensitive). */
	List<Hotel> getAllByCityName(String cityName);

	/** @return all the hotels which city name contains... */
	List<Hotel> getAllByCityNameContaining(String cityNameContaining);

}
