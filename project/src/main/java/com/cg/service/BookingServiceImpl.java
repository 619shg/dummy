package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.BookingDAO;
import com.cg.entity.Dinning;
import com.cg.entity.Resort;

@Transactional
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingDAO bookingDAO;

	@Override
	public void bookResort(Resort r, long id) {
		bookingDAO.bookResort(r, id);
	}

	@Override
	public void bookResort(Dinning d, long id) {
		bookingDAO.bookDinning(d, id);
	}

}
