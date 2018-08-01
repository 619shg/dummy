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
	public Resort bookResort(Resort resort, long guestID) {
		return bookingDAO.bookResort(resort, guestID);
	}

	@Override
	public Resort updateBookResort(Resort resort, long resortBookingId) {
		return bookingDAO.updateBookResort(resort, resortBookingId);

	}

	@Override
	public Resort cancelBookResort(long resortBookingId) {
		return bookingDAO.cancelBookResort(resortBookingId);
	}

	@Override
	public Dinning bookDinning(Dinning dinning, long dinningBookingId) {
		return bookingDAO.bookDinning(dinning, dinningBookingId);

	}

	@Override
	public Dinning updateBookedDinning(Dinning dinning, long dinningBookingId) {
		return bookingDAO.updateBookedDinning(dinning, dinningBookingId);
	}

	@Override
	public Dinning cancelBookedDinning(long dinningBookingId) {
		return bookingDAO.cancelBookedDinning(dinningBookingId);
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
