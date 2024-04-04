package com.ib.test.hrs.mvphotelbooking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ib.test.hrs.mvphotelbooking.dao.BookingDAO;
import com.ib.test.hrs.mvphotelbooking.model.Booking;
import com.ib.test.hrs.mvphotelbooking.model.City;
import com.ib.test.hrs.mvphotelbooking.model.Customer;
import com.ib.test.hrs.mvphotelbooking.model.Hotel;
import com.ib.test.hrs.mvphotelbooking.service.impl.BookingServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceImplTests {

	private static final long VALID_BOOKING_ID = 7777L;
	private static final long INVALID_BOOKING_ID = 8888L;

	private static final LocalDate BOOKING_BEGIN_DATE = LocalDate.of(2025, 4, 1);
	private static final int BOOKING_DAYS = 14;
	private static final double BOOKING_PRICE = 1234.56;
	private static final String BOOKING_DETAILS = "Mocking book";

	private static final City MOCK_CITY_1 = new City(7L, "12345", "Mocking-City", "Mockistan");
	private static final City MOCK_CITY_2 = new City(8L, "67890", "Mocking-Destination", "New Mocking Islands");
	private static final Customer MOCK_CUSTOMER = new Customer(77L, "Gus", "TOMER", "0987654321", "Mocking customer address", MOCK_CITY_1);
	private static final Hotel MOCK_HOTEL = new Hotel(777L, "Mocking Palace", "Mocking desc", "Mocking hotel address", MOCK_CITY_2);
	private static final Booking SAVED_MOCK_BOOKING = new Booking(VALID_BOOKING_ID, MOCK_CUSTOMER, MOCK_HOTEL, BOOKING_BEGIN_DATE, BOOKING_DAYS, 3, BOOKING_PRICE, BOOKING_DETAILS);
	private static final Booking NEW_MOCK_BOOKING = new Booking(MOCK_CUSTOMER, MOCK_HOTEL, BOOKING_BEGIN_DATE, BOOKING_DAYS, 3, BOOKING_PRICE, BOOKING_DETAILS);

	@TestConfiguration
	static class BookingServiceImplTestContextConfiguration {
		@Bean
		public BookingService bookingService() {
			return new BookingServiceImpl();
		}
	}

	@Autowired
	private BookingService bookingService;

	@MockBean
	private BookingDAO bookingDAO;

	@Before
	public void setUp() {
		// Save new entity
		Mockito
			.when(bookingDAO.save(Mockito.eq(NEW_MOCK_BOOKING)))
			.thenReturn(SAVED_MOCK_BOOKING);
		// Request with a valid booking id:
		Mockito
			.when(bookingDAO.getById(VALID_BOOKING_ID))
			.thenReturn(SAVED_MOCK_BOOKING);
		// Request with an invalid booking id:
		Mockito
			.when(bookingDAO.getById(INVALID_BOOKING_ID))
			.thenThrow(new EntityNotFoundException("Mock Entity Not found"));
	}

	// == Tests for "Create Hotel Booking" ==

	@Test
	public void insertBookingUsingEntity_saveTest() {
		Booking b = bookingService.save(NEW_MOCK_BOOKING);
		assertNotNull(b);
		assertEquals(SAVED_MOCK_BOOKING, b);
	}

	@Test
	public void insertBookingUsingFields_bookTest() {
		Booking b = bookingService.book(MOCK_CUSTOMER, MOCK_HOTEL, BOOKING_BEGIN_DATE, BOOKING_DAYS, BOOKING_DETAILS, 3, BOOKING_PRICE);
		assertNotNull(b);
		assertEquals(VALID_BOOKING_ID, b.getId());
		assertEquals(BOOKING_DETAILS, b.getDetails());
		assertEquals(BOOKING_BEGIN_DATE, b.getBeginDate());
		assertEquals(BOOKING_BEGIN_DATE.plusDays(BOOKING_DAYS), b.getEndDate());
		assertEquals(BOOKING_PRICE, b.getPrice(), 0.01);
	}

	// == Tests for "View Hotel Booking Details" ==

	@Test
	public void whenValidId_thenBookingShouldBeFound() {
		Booking b = bookingService.getById(VALID_BOOKING_ID);
		assertNotNull(b);
		assertEquals(SAVED_MOCK_BOOKING, b);
	}

	@Test
	public void wheninValidId_thenExceptionShouldBeThrown() {
		try {
			Booking b = bookingService.getById(INVALID_BOOKING_ID);
			assertNull("Not only an exception has not occurred but an entity was returned!", b);
		} catch (EntityNotFoundException e) {
			return; // Success - the Exception have been propagated.
		}
		fail("An EntityNotFoundException should have been thrown");
	}

}
