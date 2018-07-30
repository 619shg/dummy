package com.cg.dao;

import java.util.List;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;

public interface BookingDAO {

	public long bookResort(Resort resort, long guestID);

	public void updateBookResort(Resort resort, long resortBookingId);

	public void bookDinning(Dinning dinning, long guestID);

	public void updateBookDinning(Dinning d, long dinningBookingId);

	public List<Resort> getAllResortDetails(long guestID);

	public List<Dinning> getAllDinningDetails(long guestID);

}
