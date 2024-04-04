package com.ib.test.hrs.mvphotelbooking.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ib.test.hrs.mvphotelbooking.model.Booking;

/**
 * DAO for the {@link Booking} entity.
 * 
 * @author Igal BITAN
 */
@Repository
@Component
public interface BookingDAO extends JpaRepository<Booking, Long> {

	// Unused for the moment
	/**
	 * Request with search criteria and paging options.
	 * 
	 * @param specification the search criteria
	 * @param pageable      the paging options
	 * @return the page with the list of {@link Booking} results of the request
	 */
	Page<Booking> findAll(Specification<Booking> specification, Pageable pageable);

	/**
	 * Request all the bookings of a specified customer.
	 * 
	 * @param customerId the specified customer id
	 * @returnthe list of {@link Booking} entities linked to the specified customer
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM booking WHERE customer_id = :customerId")
	List<Booking> findByCustomerId(long customerId);

}
