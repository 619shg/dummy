package com.cg.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.GuestDAO;
import com.cg.entity.Guest;

@Transactional
@Service
public class GuestServiceImpl implements GuestService {

	private static final Logger LOGGER = LogManager.getLogger(GuestServiceImpl.class);

	@Autowired
	private GuestDAO guestDAO;

	@Override
	public Guest registerGuest(Guest guest) {
		LOGGER.info("GuestServiceImpl: executing registerGuest method.");
		return guestDAO.registerGuest(guest);
	}

	@Override
	public Guest validateGuest(String em, String pass) {
		LOGGER.info("GuestServiceImpl: executing validateGuest method.");
		return guestDAO.validateGuest(em, pass);
	}

}