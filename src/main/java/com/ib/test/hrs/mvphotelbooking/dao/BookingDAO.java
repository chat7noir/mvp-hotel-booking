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

	Page<Booking> findAll(Specification<Booking> specification, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM booking WHERE customer_id = :customerId")
	List<Booking> findByCustomerId(long customerId);

}
