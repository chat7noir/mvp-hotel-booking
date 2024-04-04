package com.ib.test.hrs.mvphotelbooking.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ib.test.hrs.mvphotelbooking.MvpHotelBookingApplication;
import com.ib.test.hrs.mvphotelbooking.dao.BookingDAO;
import com.ib.test.hrs.mvphotelbooking.dao.HotelDAO;
import com.ib.test.hrs.mvphotelbooking.model.Booking;
import com.ib.test.hrs.mvphotelbooking.model.City;
import com.ib.test.hrs.mvphotelbooking.model.Customer;
import com.ib.test.hrs.mvphotelbooking.model.Hotel;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = MvpHotelBookingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class BookingControllerTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private HotelDAO hotelDAO;

	@Autowired
	private BookingDAO bookingDAO;

	// FIXME - java.lang.IllegalStateException: Failed to load ApplicationContext
	//@Test
	public void givenHotels_whenGetHotels_thenStatus200() throws Exception {
		createTestHotels();

		mvc.perform(get("/hotels/destination/paris").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Shilton Paris")));
	}

	private void createTestHotels() {
		// Cities definition
		final City ct1 = new City(1, "Paris", "75000", "France");
		final City ct2 = new City(2, "Hanoi", "120000", "Vietnam");
		final City ct3 = new City(3, "Ho Chi Minh", "70000", "Vietnam");
		final City ct4 = new City(4, "Vung Tau", "78000", "Vietnam");

		// Hotels definition
		final Hotel h11 = new Hotel(11, "Shilton Paris", "Un hôtel de test fameux", "123 rue du Test", ct1);
		final Hotel h12 = new Hotel(12, "UBU Paris", "Un hôtel pas si fameux", "45 rue des Essais", ct1);
		final Hotel h13 = new Hotel(13, "Le Fritz Paris", "Un hôtel de test cher", "99 avenue de la Validation", ct1);
		final Hotel h14 = new Hotel(14, "Le Georges Saint", "Un hôtel de test royal", "77 faubourg des Tests", ct1);
		final Hotel h21 = new Hotel(21, "Shilton Al Hanoi", "Un hôtel de test fameux", "44 Đ. Mạc Thiên Tích", ct2);
		final Hotel h22 = new Hotel(22, "UBU Hanoi", "Un hôtel pas si fameux", "45 Đ. Mạc Tích Thiên", ct2);
		final Hotel h23 = new Hotel(23, "Le Fritz Hanoi", "Un hôtel de test cher", "66 Đ. Trần Hưng", ct2);
		final Hotel h24 = new Hotel(24, "Le Victoria Palace", "Un hôtel de test royal", "77 Đ. Trần Hưng Đạo", ct2);
		final Hotel h31 = new Hotel(31, "Shilton HCM", "Un hôtel de test fameux", "442 Đ. Mạc Thiên Tích", ct3);
		final Hotel h32 = new Hotel(32, "UBU HCM", "Un hôtel pas si fameux", "453 Đ. Mạc Tích Thiên", ct3);
		final Hotel h33 = new Hotel(33, "Le Fritz HCM", "Un hôtel de test cher", "667 Đ. Trần Hưng", ct3);
		final Hotel h34 = new Hotel(34, "Marine Hotel", "Un hôtel bateau", "1234 Đ. Trần Hưng Đạo, Phường 10, Quận 5", ct3);
		final Hotel h41 = new Hotel(41, "Shilton Ba Ria", "Un hôtel de test fameux", "440 Đ. Mạc Thiên Tích", ct4);
		final Hotel h42 = new Hotel(42, "UBU Vung Tau", "Un hôtel pas trop trop chic", "405 Đ. Mạc Tích Thiên", ct4);
		final Hotel h43 = new Hotel(43, "Le Fritz Ba Ria", "Un hôtel de test cher", "606 Đ. Trần Hưng", ct4);
		final Hotel h44 = new Hotel(44, "Le Savoy Bien", "Un hôtel de test frais", "747 Đ. Trần Hưng Đạo", ct4);

		// Customers definition
		final Customer cu1 = new Customer(1, "Igor", "FRANCK", "+33123456789", "No address", ct1);
		/* final Customer cu2 = new Customer(2, "Vy", "TRAN", "+84987654321", "No address", ct2);
		final Customer cu3 = new Customer(3, "Bernardo", "PLOPO", "+24135792468", "No address", ct4);
		final Customer cu4 = new Customer(4, "Minh", "NGUYEN", "+84112233445", "No address", ct3); */

		// Bookings definition
		final Booking bo1 = new Booking(101, cu1, h31, LocalDate.of(2023, 9, 12), 14, 2, 456.78, "No breakfast");
		final Booking bo2 = new Booking(102, cu1, h34, LocalDate.of(2024, 2, 2), 14, 1, 135.79, "No detail");

		// Save hotels and bookings
		hotelDAO.saveAll(Arrays.asList(h11, h12, h13, h14, h21, h22, h23, h24, h31, h32, h33, h34, h41, h42, h43, h44));
		bookingDAO.saveAll(Arrays.asList(bo1, bo2));
	}

}
