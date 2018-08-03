package com.cg.dao;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cg.entity.Guest;
import com.cg.entity.GuestRowMapper;

@Repository
public class GuestDAOImpl implements GuestDAO {

	private static final Logger LOGGER = LogManager.getLogger(GuestDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Date date = new Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	private static final String SQL_INSERT_GUEST = "INSERT INTO guest_profile(email, first_name, last_name, address, phone, password, created_date, updated_date) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public Guest registerGuest(Guest guest) {
		LOGGER.info("Executing Guest Registering .");
		try {
			guest.setCreatedDate(date);
			guest.setUpdatedDate(date);
			Object[] params = { guest.getEmail(), guest.getFirstName(), guest.getLastName(), guest.getAddress(),
					guest.getPhone(), guest.getPassword(), guest.getCreatedDate(), guest.getUpdatedDate() };
			jdbcTemplate.update(SQL_INSERT_GUEST, params);
		} catch (Exception e) {
			LOGGER.warn(e);
			return null;
		}
		Guest guest1 = jdbcTemplate.queryForObject(
				"select * from guest_profile where guest_id in(select max(guest_id) from guest_profile);",
				new GuestRowMapper());
		return guest1;
	}

	@Override
	public Guest validateGuest(String em, String pass) {
		LOGGER.info("validating guest with provided email and password");
		RowMapper<Guest> rowmapper = new GuestRowMapper();
		Guest guest = null;
		try {
			guest = jdbcTemplate.queryForObject("SELECT * FROM guest_profile WHERE email = ? AND password= ?",
					new Object[] { em, pass }, rowmapper);

		} catch (Exception e) {
			LOGGER.warn(e);
			return null;
		}

		return guest;

	}
}