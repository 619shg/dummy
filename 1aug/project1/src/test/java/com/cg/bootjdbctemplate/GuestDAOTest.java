package com.cg.bootjdbctemplate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cg.dao.GuestDAOImpl;
import com.cg.entity.Guest;

@RunWith(MockitoJUnitRunner.class)
public class GuestDAOTest {

	@Mock
	JdbcTemplate jdbcTemplate;

	@InjectMocks
	GuestDAOImpl daoImpl;

	@Test
	public void testRegisterGuest() {

		Guest guest = new Guest();
		guest.setEmail("sg@cg.com");
		guest.setFirstName("Sourabh");
		guest.setLastName("Gour");
		guest.setAddress("Nagpur");
		guest.setPhone("+918983276345");
		guest.setPassword("sourabh");

		Guest testGuestObj = daoImpl.registerGuest(guest);

		assertEquals("Test: ", guest, testGuestObj);
	}

}
