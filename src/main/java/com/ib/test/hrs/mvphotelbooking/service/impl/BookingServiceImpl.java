package com.ib.test.hrs.mvphotelbooking.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ib.test.hrs.mvphotelbooking.dao.BookingDAO;
import com.ib.test.hrs.mvphotelbooking.model.Booking;
import com.ib.test.hrs.mvphotelbooking.model.Customer;
import com.ib.test.hrs.mvphotelbooking.model.Hotel;
import com.ib.test.hrs.mvphotelbooking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookingDAO bookingDAO;

	@Override
	public Booking book(Customer customer, Hotel hotel, LocalDate beginDate, int days, String details, int guests, double price) {
		final Booking booking = new Booking(customer, hotel, beginDate, days, guests, price, details);
		return this.save(booking);
	}

	@Override
	public Booking save(Booking booking) {
		logger.debug("Saving booking: {}", booking);
		return this.bookingDAO.save(booking);
	}

	@Override
	public Booking getById(long id) {
		return this.loadLazyJoins(this.bookingDAO.getById(id));
	}

	@Override
	public List<Booking> getAllByCustomerId(long customerId) {
		return this.bookingDAO.findByCustomerId(customerId);
	}

	private Booking loadLazyJoins(Booking booking) {
		if (booking != null) {
			Hibernate.initialize(booking.getCustomer());
			Hibernate.initialize(booking.getCustomer().getCity());
		}
		return booking;
	}

}
