package com.cg.service;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;

public interface BookingService {

	public void bookResort(Resort r, long id);

	public void updateBookResort(Resort r, long r_id);

	public void bookDinning(Dinning d, long d_id);

	public void updateBookDinning(Dinning d, long d_id);

}
