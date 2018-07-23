package com.cg.service;

import com.cg.entity.Guest;

public interface GuestService {

	public void registerGuest(Guest guest);

	public boolean validateGuest(String email, String password);

}
