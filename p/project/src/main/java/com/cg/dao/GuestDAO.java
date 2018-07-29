package com.cg.dao;

import com.cg.entity.Guest;

public interface GuestDAO {

	public long registerGuest(Guest guest);
	public long checkGuest(Guest guest);

	public long validateGuest(String em, String pass);

}
