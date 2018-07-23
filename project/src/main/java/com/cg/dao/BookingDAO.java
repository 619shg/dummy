package com.cg.dao;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;

public interface BookingDAO {

	public void bookResort(Resort r, long id);

	public void bookDinning(Dinning d, long id);

}
