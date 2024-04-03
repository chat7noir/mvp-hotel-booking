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

	Page<Hotel> findAll(Specification<Hotel> specification, Pageable pageable);

	@Query("select h from Hotel h where LOWER(h.city.name) = LOWER(?1)")
	List<Hotel> findByCityName(String cityName);

	@Query("select h from Hotel h where LOWER(h.city.name) like LOWER(?1)")
	List<Hotel> findByCityNameLike(String cityNameContaining);

}
