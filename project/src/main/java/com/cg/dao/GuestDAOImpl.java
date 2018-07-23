package com.cg.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.entity.Guest;

@Repository
public class GuestDAOImpl implements GuestDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT_GUEST = "INSERT INTO guest_profile(email, first_name, last_name, address, phone, password, created_date, updated_date) values (?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public void registerGuest(Guest guest) {
		Object[] params = { guest.getEmail(), guest.getFirstName(), guest.getLastName(), guest.getAddress(),
				guest.getPhone(), guest.getPassword(), guest.getCreatedDate(), guest.getUpdatedDate() };
		jdbcTemplate.update(SQL_INSERT_GUEST, params);
	}

	@Override
	public boolean validateGuest(String em, String pass) {
		Guest g = new Guest();
		String sql = "SELECT email,password from guest_profile where email=? and password=?";
		Object[] params = { g.getEmail(), g.getPassword() };
		// jdbcTemplate.query(sql,);
		if (em == g.getEmail() && pass == g.getPassword())
			return true;
		return false;
	}

}