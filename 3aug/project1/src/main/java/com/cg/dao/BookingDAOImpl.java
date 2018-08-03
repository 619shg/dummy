package com.cg.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.entity.Dinning;
import com.cg.entity.DinningRowMapper;
import com.cg.entity.Resort;
import com.cg.entity.ResortRowMapper;

@Repository
public class BookingDAOImpl implements BookingDAO {
	private static final Logger LOGGER = Logger.getLogger("BookingController.class");

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Date date = new Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	private static final String SQL_BOOK_RESORT_BY_ID = "INSERT INTO resort_reservation(guest_id_fk, room_type, arrival_date, departure_date, no_of_people, status, created_date, updated_date)"
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE_BOOKED_RESORT_BY_REGISTER_ID = "UPDATE resort_reservation SET room_type = ?, arrival_date=?, departure_date=?, "
			+ "no_of_people =?, updated_date=? where r_reservation_number = ?";

	private static final String SQL_BOOK_DINNING_BY_ID = "INSERT INTO dinning_reservation(guest_id_fk, dinning_type, arrival_date, no_of_people, status, created_date, updated_date)"
			+ "values (?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE_BOOKED_DINNING_BY_REGISTER_ID = "UPDATE dinning_reservation SET dinning_type = ?, arrival_date=?,"
			+ "no_of_people =?, updated_date=? where d_reservation_number = ?";

	@Override
	public Resort bookResort(Resort resort, long guestID) {
		LOGGER.info("Executing Resort Booking.");
		try {
			resort.setGuestID(guestID);
			resort.setStatus("booked");
			resort.setCreatedDate(date);
			resort.setUpdatedDate(date);

			Object[] params = { resort.getGuestID(), resort.getRoomType(), resort.getArrivalDate(),
					resort.getDepartureDate(), resort.getNoOfPeople(), resort.getStatus(), resort.getCreatedDate(),
					resort.getUpdatedDate() };

			jdbcTemplate.update(SQL_BOOK_RESORT_BY_ID, params);
		} catch (Exception e) {
			return null;
		}
		return getResort();
	}

	@Override
	public Resort updateBookResort(Resort resort, long resortBookingId) {
		LOGGER.info("Updating Booked Resort.");
		try {
			resort.setrReservationNumber(resortBookingId);
			resort.setUpdatedDate(date);
			Object[] params = { resort.getRoomType(), resort.getArrivalDate(), resort.getDepartureDate(),
					resort.getNoOfPeople(), resort.getUpdatedDate(), resort.getrReservationNumber() };

			jdbcTemplate.update(SQL_UPDATE_BOOKED_RESORT_BY_REGISTER_ID, params);
		} catch (Exception e) {
			return null;
		}
		return getResort();
	}

	@Override
	public Resort cancelBookResort(long resortBookingId) {
		LOGGER.info("Cancelling Booked Resort.");
		String status = "cancelled";
		String query = "UPDATE resort_reservation SET status=? where r_reservation_number=" + resortBookingId + "";

		try {
			jdbcTemplate.update(query, status);
		} catch (Exception e) {
			return null;
		}
		return getResort();
	}

	@Override
	public Dinning bookDinning(Dinning dinning, long guestID) {
		LOGGER.info("Executing Dinning Booking.");
		try {
			dinning.setGuestID(guestID);
			dinning.setStatus("booked");
			dinning.setCreatedDate(date);
			dinning.setUpdatedDate(date);

			Object[] params = { dinning.getGuestID(), dinning.getDinningType(), dinning.getArrivalDate(),
					dinning.getNoOfPeople(), dinning.getStatus(), dinning.getCreatedDate(), dinning.getUpdatedDate() };

			jdbcTemplate.update(SQL_BOOK_DINNING_BY_ID, params);
		} catch (Exception e) {
			return null;
		}
		return getDinning();
	}

	@Override
	public Dinning updateBookedDinning(Dinning dinning, long dinningBookingId) {
		LOGGER.info("Updating Booked Dinning.");
		dinning.setdReservationNumber(dinningBookingId);
		dinning.setUpdatedDate(date);

		Object[] params = { dinning.getDinningType(), dinning.getArrivalDate(), dinning.getNoOfPeople(),
				dinning.getUpdatedDate(), dinning.getdReservationNumber() };

		jdbcTemplate.update(SQL_UPDATE_BOOKED_DINNING_BY_REGISTER_ID, params);
		return getDinning();
	}

	@Override
	public Dinning cancelBookedDinning(long dinningBookingId) {
		LOGGER.info("Cancelling Booked Dinning.");
		String status = "cancelled";
		String query = "UPDATE dinning_reservation SET status=? where d_reservation_number=" + dinningBookingId + "";
		try {
			jdbcTemplate.update(query, status);
		} catch (Exception e) {
			return null;
		}
		return getDinning();
	}

	// helper method
	public Resort getResort() {
		Resort resort = jdbcTemplate.queryForObject(
				"select * from resort_reservation where r_reservation_number in(select max(r_reservation_number) from resort_reservation);",
				new ResortRowMapper());
		return resort;
	}

	public Dinning getDinning() {
		Dinning dinning = jdbcTemplate.queryForObject(
				"select * from dinning_reservation where d_reservation_number in(select max(d_reservation_number) from dinning_reservation);",
				new DinningRowMapper());
		return dinning;
	}

	@Override
	public List<Resort> getAllResortDetails(long guestID) {
		LOGGER.info("Viewing all resort details for a guest");
		String query = "SELECT * FROM resort_reservation WHERE guest_id_fk=" + guestID + "";
		List<Resort> resort = jdbcTemplate.query(query, new ResortRowMapper());
		return resort;
	}

	@Override
	public List<Dinning> getAllDinningDetails(long guestID) {
		LOGGER.info("Viewing all dinning details for a guest");
		String query = "SELECT * FROM dinning_reservation WHERE guest_id_fk=" + guestID + "";
		List<Dinning> dinning = jdbcTemplate.query(query, new DinningRowMapper());
		return dinning;
	}
}