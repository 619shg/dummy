package com.cg.service;

import java.util.List;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;

public interface BookingService {

	public long bookResort(Resort resort, long guestID);

	public void updateBookResort(Resort resort, long resortBookingId);

	public void bookDinning(Dinning dinning, long guestID);

	public void updateBookDinning(Dinning dinning, long dinningBookingId);

	public List<Resort> getAllResortDetails(long guestID);

	public List<Dinning> getAllDiningDetails(long guestID);

}
