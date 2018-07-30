package com.cg.service;

import java.util.List;

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
	public long bookResort(Resort resort, long guestID) {
		return bookingDAO.bookResort(resort, guestID);
	}

	@Override
	public void updateBookResort(Resort resort, long resortBookingId) {
		bookingDAO.updateBookResort(resort, resortBookingId);

	}

	@Override
	public void bookDinning(Dinning dinning, long dinningBookingId) {
		bookingDAO.bookDinning(dinning, dinningBookingId);

	}

	@Override
	public void updateBookDinning(Dinning dinning, long dinningBookingId) {
		bookingDAO.updateBookDinning(dinning, dinningBookingId);
	}

	@Override
	public List<Resort> getAllResortDetails(long guest_id) {

		return bookingDAO.getAllResortDetails(guest_id);
	}

	@Override
	public List<Dinning> getAllDiningDetails(long guest_id) {
		return bookingDAO.getAllDinningDetails(guest_id);
	}

}
