package com.ib.test.hrs.mvphotelbooking.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ib.test.hrs.mvphotelbooking.dao.HotelDAO;
import com.ib.test.hrs.mvphotelbooking.model.Hotel;
import com.ib.test.hrs.mvphotelbooking.service.HotelService;

/**
 * Basic implementation of the service interface for the {@link Hotel} entity.
 * 
 * @author Igal BITAN
 */
@Service
public class HotelServiceImpl implements HotelService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HotelDAO hotelDAO;

	@Override
	public Hotel getById(long id) {
		logger.debug("Get the hotel with id = {}", id);
		return this.hotelDAO.getById(id);
	}

	@Override
	public List<Hotel> getAllByCityName(String cityName) {
		logger.debug("Searching all hotels in city = \"{}\"", cityName);
		return this.hotelDAO.findByCityName(cityName);
	}

	@Override
	public List<Hotel> getAllByCityNameContaining(String cityNameContaining) {
		logger.debug("Searching all hotels in city which name contains \"{}\"", cityNameContaining);
		return this.hotelDAO.findByCityNameLike('%' + cityNameContaining + '%');
	}

}
