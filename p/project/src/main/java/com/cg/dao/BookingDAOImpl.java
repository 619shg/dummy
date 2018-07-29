package com.cg.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;

@Repository
public class BookingDAOImpl implements BookingDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	Date date = new Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	private final String SQL_BOOK_RESORT_BY_ID = "INSERT INTO resort_reservation(guest_id_fk, room_type, arrival_date, departure_date, no_of_people, status, created_date, updated_date)"
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

	private final String SQL_UPDATE_BOOKED_RESORT_BY_REGISTER_ID = "UPDATE resort_reservation SET room_type = ?, arrival_date=?, departure_date=?, "
			+ "no_of_people =?, updated_date=? where r_reservation_number = ?";

	private final String SQL_BOOK_DINNING_BY_ID = "INSERT INTO dinning_reservation(guest_id_fk, dinning_type, arrival_date, no_of_people, status, created_date, updated_date)"
			+ "values (?, ?, ?, ?, ?, ?, ?)";

	private final String SQL_UPDATE_BOOKED_DINNING_BY_REGISTER_ID = "UPDATE dinning_reservation SET dinning_type = ?, arrival_date=?,"
			+ "no_of_people =?, updated_date=? where d_reservation_number = ?";

	@Override
	public void bookResort(Resort r, long id) {

		r.setGuestID(id);
		r.setStatus("booked");
		r.setCreatedDate(date);
		r.setUpdatedDate(date);

		Object[] params = { r.getGuestID(), r.getRoomType(), r.getArrivalDate(), r.getDepartureDate(),
				r.getNoOfPeople(), r.getStatus(), r.getCreatedDate(), r.getUpdatedDate() };

		jdbcTemplate.update(SQL_BOOK_RESORT_BY_ID, params);
	}

	@Override
	public void updateBookResort(Resort r, long r_id) {

		r.setrReservationNumber(r_id);
		r.setUpdatedDate(date);
		Object[] params = { r.getRoomType(), r.getArrivalDate(), r.getDepartureDate(), r.getNoOfPeople(),
				r.getUpdatedDate(), r.getrReservationNumber() };

		jdbcTemplate.update(SQL_UPDATE_BOOKED_RESORT_BY_REGISTER_ID, params);

	}

	@Override
	public void bookDinning(Dinning d, long id) {

		d.setGuestID(id);
		d.setStatus("booked");
		d.setCreatedDate(date);
		d.setUpdatedDate(date);

		Object[] params = { d.getGuestID(), d.getDinningType(), d.getArrivalDate(), d.getNoOfPeople(), d.getStatus(),
				d.getCreatedDate(), d.getUpdatedDate() };

		jdbcTemplate.update(SQL_BOOK_DINNING_BY_ID, params);
	}

	@Override
	public void updateBookDinning(Dinning d, long d_id) {
		d.setdReservationNumber(d_id);
		d.setUpdatedDate(date);

		Object[] params = { d.getDinningType(), d.getArrivalDate(), d.getNoOfPeople(), d.getUpdatedDate(),
				d.getdReservationNumber() };

		jdbcTemplate.update(SQL_UPDATE_BOOKED_DINNING_BY_REGISTER_ID, params);

	}

}