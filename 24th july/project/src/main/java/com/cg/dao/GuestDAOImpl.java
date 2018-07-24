package com.cg.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cg.entity.Guest;
import com.cg.entity.GuestRowMapper;

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
	public long validateGuest(String em, String pass) {
		RowMapper<Guest> rowmapper = new GuestRowMapper();
		long id = 0;
		try {
			Guest guest = jdbcTemplate.queryForObject("SELECT * FROM guest_profile WHERE email = ? AND password= ?",
					new Object[] { em, pass }, rowmapper);
			id = guest.getGuestID();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return id;

	}
}