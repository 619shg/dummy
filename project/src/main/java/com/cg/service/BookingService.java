package com.cg.service;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;

public interface BookingService {

	public void bookResort(Resort r, long id);

	public void bookResort(Dinning d, long id);

}
