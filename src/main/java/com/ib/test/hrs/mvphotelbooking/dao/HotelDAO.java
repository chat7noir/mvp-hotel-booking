package com.ib.test.hrs.mvphotelbooking.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ib.test.hrs.mvphotelbooking.model.Hotel;

/**
 * DAO for the {@link Hotel} entity.
 * 
 * @author Igal BITAN
 */
@Repository
@Component
public interface HotelDAO extends JpaRepository<Hotel, Long> {

	// Unused for the moment
	/**
	 * Request with search criteria and paging options.
	 * 
	 * @param specification the search criteria
	 * @param pageable      the paging options
	 * @return the page with the list of {@link Hotel} results of the request
	 */
	Page<Hotel> findAll(Specification<Hotel> specification, Pageable pageable);

	/**
	 * Request all the hotels in a destination from the city name (case insensitive).
	 * 
	 * @param cityName the name of the city (destination) where the searched hotels are
	 * @return the list of {@link Hotel} entities in the searched destination
	 */
	@Query("select h from Hotel h where LOWER(h.city.name) = LOWER(?1)")
	List<Hotel> findByCityName(String cityName);

	/**
	 * Request all the hotels in a destination whose city name matches a searching string (case insensitive).
	 * 
	 * @param cityNameContaining the searching string for the name of the cities (destinations) where the searched hotels are
	 * @return the list of {@link Hotel} entities in the searched destination
	 */
	@Query("select h from Hotel h where LOWER(h.city.name) like LOWER(?1)")
	List<Hotel> findByCityNameLike(String cityNameContaining);

}
