package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.GuestDAO;
import com.cg.entity.Guest;

@Transactional
@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDAO guestDAO;

	@Override
	public void registerGuest(Guest guest) {
		guestDAO.registerGuest(guest);
	}

	@Override
	public long validateGuest(String em, String pass) {
		return guestDAO.validateGuest(em, pass);
	}

}