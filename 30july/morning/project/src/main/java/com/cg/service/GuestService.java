package com.cg.service;

import com.cg.entity.Guest;

public interface GuestService {

	public long registerGuest(Guest guest);

	public long validateGuest(String em, String pass);

	public long validateGuestID(long guestID);

}
