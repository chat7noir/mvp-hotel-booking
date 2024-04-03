package com.ib.test.hrs.mvphotelbooking.service;

import java.time.LocalDate;
import java.util.List;

import com.ib.test.hrs.mvphotelbooking.model.Booking;
import com.ib.test.hrs.mvphotelbooking.model.Customer;
import com.ib.test.hrs.mvphotelbooking.model.Hotel;

/**
 * Service pour l'entité {@link Booking}
 * 
 * @author Igal BITAN
 */
public interface BookingService {

	/**
	 * Create and save a booking entity.
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
	 */
	Booking save(Booking booking);

	/** @return the booking by its id */
	Booking getById(long id);

	/** @return all the bookings for a specified customer id */
	List<Booking> getAllByCustomerId(long customerId);

}
