package com.cg.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;

@Repository
public class BookingDAOImpl implements BookingDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_BOOK_RESORT_BY_ID = "INSERT INTO resort_reservation(guest_id_fk, room_type, arrival_date, departure_date, no_of_people, status, created_date, updated_date) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

	private final String SQL_BOOK_DINNING_BY_ID = "INSERT INTO dinning_reservation(guest_id_fk, dinning_type, arrival_date, no_of_people, status, created_date, updated_date) "
			+ "values (?, ?, ?, ?, ?, ?, ?)";

	private final String SQL_UPDATE_BOOK_RESORT_BY_ID = "UPDATE resort_reservation SET dinning_type = ?, arrival_date = ?, no_of_people =?, status=?, updated_date= where r_reservation_number = ?";

	@Override
	public void bookResort(Resort r, long id) {
		r.setGuestID(id);
		Object[] params = { r.getGuestID(), r.getRoomType(), r.getArrivalDate(), r.getDepartureDate(),
				r.getNoOfPeople(), r.getStatus(), r.getCreatedDate(), r.getUpdatedDate() };
		jdbcTemplate.update(SQL_BOOK_RESORT_BY_ID, params);
	}

	@Override
	public void updateBookResort(Resort r, long r_id, long g_id) {
		r.setGuestID(g_id);
		r.setrReservationNumber(r_id);

		Object[] params = { r.getGuestID(), r.getrReservationNumber(), r.getRoomType(), r.getArrivalDate(),
				r.getDepartureDate(), r.getNoOfPeople(), r.getStatus(), r.getCreatedDate(), r.getUpdatedDate() };
		jdbcTemplate.update(SQL_BOOK_RESORT_BY_ID, params);
	}

	@Override
	public void bookDinning(Dinning d, long id) {
		d.setGuestID(id);
		Object[] params = { d.getGuestID(), d.getDinningType(), d.getArrivalDate(), d.getNoOfPeople(), d.getStatus(),
				d.getCreatedDate(), d.getUpdatedDate() };
		jdbcTemplate.update(SQL_BOOK_DINNING_BY_ID, params);

	}

}
