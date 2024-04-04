package com.ib.test.hrs.mvphotelbooking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

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
		City city1 = new City(7L, "12345", "Mocking-City", "Mockistan");
		City city2 = new City(8L, "67890", "Mocking-Destination", "New Mocking Islands");
		Customer customer = new Customer(77L, "Gus", "TOMER", "0987654321", "Mocking address", city1);
		Hotel hotel = new Hotel(777L, null, null, null, city2);
		Booking booking = new Booking(7777L, customer, hotel, LocalDate.of(2025, 4, 1), 14, 3, 1234.56, "Mocking book");

		Mockito
			.when(bookingDAO.getById(7777L))
			.thenReturn(booking);
	}

	@Test
	public void whenValidId_thenBookingShouldBeFound() {
		Booking b = bookingService.getById(7777L);
		assertNotNull(b);
		assertEquals(7777L, b.getId());
		assertEquals("Mocking book", b.getDetails());
		assertEquals(LocalDate.of(2025, 4, 1), b.getBeginDate());
		assertEquals(LocalDate.of(2025, 4, 15), b.getEndDate());
	}

}
