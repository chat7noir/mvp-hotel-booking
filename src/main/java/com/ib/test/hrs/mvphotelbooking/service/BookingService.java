package com.ib.test.hrs.mvphotelbooking.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.ib.test.hrs.mvphotelbooking.model.Booking;
import com.ib.test.hrs.mvphotelbooking.model.Customer;
import com.ib.test.hrs.mvphotelbooking.model.Hotel;

/**
 * Service interface for the {@link Booking} entity
 * 
 * @author Igal BITAN
 */
public interface BookingService {

	/**
	 * Create and save a booking entity with all its fields in parameter.
	 * 
	 * @param customer  the customer who books
	 * @param hotel     the hotel booked
	 * @param beginDate the booking start date
	 * @param days      the number of days booked
	 * @param details   (optional) details of the booking
	 * @param guests    the number of guests
	 * @param price     the total price
	 * @return the new {@link Booking} entity
	 */
	Booking book(Customer customer, Hotel hotel, LocalDate beginDate, int days, String details, int guests, double price);

	/**
	 * Save a booking entity.
	 * 
	 * @param booking the {@link Booking} entity to save
	 * @return the saved {@link Booking} entity
	 */
	Booking save(Booking booking);

	/**
	 * Retrieve the {@link Booking} entity from its id.
	 * 
	 * @param id the specified booking id
	 * @return the booking by its id
	 * @throws EntityNotFoundException if there is no {@link Booking} entity with the specified id
	 */
	Booking getById(long id) throws EntityNotFoundException;

	/**
	 * Retrieve all the bookings of a specified customer.
	 * 
	 * @param customerId the specified customer id
	 * @returnthe list of {@link Booking} entities linked to the specified customer
	 */
	List<Booking> getAllByCustomerId(long customerId);

}
