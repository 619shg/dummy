package com.cg.service;

import com.cg.entity.Guest;

public interface GuestService {

	public long registerGuest(Guest guest);

	public long validateGuest(String email, String password);

}
