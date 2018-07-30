package com.cg.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.entity.Dinning;
import com.cg.entity.DinningRowMapper;
import com.cg.entity.Resort;
import com.cg.entity.ResortRowMapper;

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
	public long bookResort(Resort resort, long guestID) {

		resort.setGuestID(guestID);
		resort.setStatus("booked");
		resort.setCreatedDate(date);
		resort.setUpdatedDate(date);

		Object[] params = { resort.getGuestID(), resort.getRoomType(), resort.getArrivalDate(),
				resort.getDepartureDate(), resort.getNoOfPeople(), resort.getStatus(), resort.getCreatedDate(),
				resort.getUpdatedDate() };

		long bookedResortID = jdbcTemplate.update(SQL_BOOK_RESORT_BY_ID, params);
		return bookedResortID;
	}

	@Override
	public void updateBookResort(Resort resort, long resortBookingId) {

		resort.setrReservationNumber(resortBookingId);
		resort.setUpdatedDate(date);
		Object[] params = { resort.getRoomType(), resort.getArrivalDate(), resort.getDepartureDate(),
				resort.getNoOfPeople(), resort.getUpdatedDate(), resort.getrReservationNumber() };

		jdbcTemplate.update(SQL_UPDATE_BOOKED_RESORT_BY_REGISTER_ID, params);

	}

	@Override
	public void bookDinning(Dinning dinning, long guestID) {

		dinning.setGuestID(guestID);
		dinning.setStatus("booked");
		dinning.setCreatedDate(date);
		dinning.setUpdatedDate(date);

		Object[] params = { dinning.getGuestID(), dinning.getDinningType(), dinning.getArrivalDate(),
				dinning.getNoOfPeople(), dinning.getStatus(), dinning.getCreatedDate(), dinning.getUpdatedDate() };

		jdbcTemplate.update(SQL_BOOK_DINNING_BY_ID, params);
	}

	@Override
	public void updateBookDinning(Dinning dinning, long dinningBookingId) {
		dinning.setdReservationNumber(dinningBookingId);
		dinning.setUpdatedDate(date);

		Object[] params = { dinning.getDinningType(), dinning.getArrivalDate(), dinning.getNoOfPeople(),
				dinning.getUpdatedDate(), dinning.getdReservationNumber() };

		jdbcTemplate.update(SQL_UPDATE_BOOKED_DINNING_BY_REGISTER_ID, params);
	}

	@Override
	public List<Resort> getAllResortDetails(long guestID) {
		String query = "SELECT * FROM resort_reservation WHERE guest_id_fk=" + guestID + "";
		List<Resort> resort = jdbcTemplate.query(query, new ResortRowMapper());
		return resort;
	}

	@Override
	public List<Dinning> getAllDinningDetails(long guestID) {
		String query = "SELECT * FROM dinning_reservation WHERE guest_id_fk=" + guestID + "";
		List<Dinning> dinning = jdbcTemplate.query(query, new DinningRowMapper());
		return dinning;
	}
}