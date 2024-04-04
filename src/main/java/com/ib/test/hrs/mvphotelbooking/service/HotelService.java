package com.ib.test.hrs.mvphotelbooking.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.ib.test.hrs.mvphotelbooking.model.Hotel;

/**
 * Service interface for the {@link Hotel} entity.
 * 
 * @author Igal BITAN
 */
public interface HotelService {

	/**
	 * Retrieve the {@link Hotel} entity from its id.
	 * 
	 * @param id the specified hotel id
	 * @return the hotel by its id
	 * @throws EntityNotFoundException if there is no {@link Hotel} entity with the specified id
	 */
	Hotel getById(long id) throws EntityNotFoundException;

	/**
	 * Search all the hotels in a destination from the city name (case insensitive).
	 * 
	 * @param cityName the name of the city (destination) where the searched hotels are
	 * @return the list of {@link Hotel} entities in the searched destination
	 */
	List<Hotel> getAllByCityName(String cityName);

	/**
	 * Search all the hotels in a destination whose city name contains a string (case insensitive).
	 * 
	 * @param cityNameContaining a part of the name of the cities (destinations) where the searched hotels are
	 * @return the list of {@link Hotel} entities in the searched destination (city name contains)
	 */
	List<Hotel> getAllByCityNameContaining(String cityNameContaining);

}
