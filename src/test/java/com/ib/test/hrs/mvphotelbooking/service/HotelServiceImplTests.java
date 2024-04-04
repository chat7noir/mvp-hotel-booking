package com.ib.test.hrs.mvphotelbooking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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

import com.ib.test.hrs.mvphotelbooking.dao.HotelDAO;
import com.ib.test.hrs.mvphotelbooking.model.City;
import com.ib.test.hrs.mvphotelbooking.model.Hotel;
import com.ib.test.hrs.mvphotelbooking.service.impl.HotelServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceImplTests {

	private static final long VALID_HOTEL_ID = 777L;
	private static final long INVALID_HOTEL_ID = 888L;

	private static final City MOCK_CITY = new City(8L, "67890", "Mocking-Destination", "New Mocking Islands");
	private static final Hotel SAVED_MOCK_HOTEL = new Hotel(VALID_HOTEL_ID, "Mocking Palace", "Mocking desc", "Mocking hotel address", MOCK_CITY);

	@TestConfiguration
	static class HotelServiceImplTestContextConfiguration {
		@Bean
		public HotelService hotelService() {
			return new HotelServiceImpl();
		}
	}

	@Autowired
	private HotelService hotelService;

	@MockBean
	private HotelDAO hotelDAO;

	@Before
	public void setUp() {
		// Request with a valid hotel id:
		Mockito
			.when(hotelDAO.getById(VALID_HOTEL_ID))
			.thenReturn(SAVED_MOCK_HOTEL);
		// Request with an invalid hotel id:
		Mockito
			.when(hotelDAO.getById(INVALID_HOTEL_ID))
			.thenThrow(new EntityNotFoundException("Mock Entity Not found"));
	}

	// == Basic get by id tests ==

	@Test
	public void whenValidId_thenHotelShouldBeFound() {
		Hotel b = hotelService.getById(VALID_HOTEL_ID);
		assertNotNull(b);
		assertEquals(SAVED_MOCK_HOTEL, b);
	}

	@Test
	public void wheninValidId_thenExceptionShouldBeThrown() {
		try {
			Hotel b = hotelService.getById(INVALID_HOTEL_ID);
			assertNull("Not only an exception has not occurred but an entity was returned!", b);
		} catch (EntityNotFoundException e) {
			return; // Success - the Exception have been propagated.
		}
		fail("An EntityNotFoundException should have been thrown");
	}

	// == Tests for "Search for Hotels" ==

	public void searchHotelsByCityNameEquals_getAllByCityName() {
		// TODO ???
	}

	public void searchHotelsByCityNameContains_getAllByCityNameContains() {
		// TODO ???
	}

}
