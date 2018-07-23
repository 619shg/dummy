package com.cg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Dinning;
import com.cg.entity.Guest;
import com.cg.entity.Resort;
import com.cg.service.BookingService;
import com.cg.service.GuestService;

@RestController
@RequestMapping("/guest")
public class GuestRestController {

	@Autowired
	private GuestService guestService;

	@Autowired
	private BookingService bookingService;

	@PostMapping("/register")
	public Guest createGuest(@RequestBody Guest guest) {
		guestService.registerGuest(guest);
		return guest;
	}

	@PostMapping("/login")
	public String processGuest(@RequestBody Guest g) {
		boolean validGuest;
		validGuest = guestService.validateGuest(g.getEmail(), g.getPassword());
		if (validGuest == true)
			return "Guest Validated and Logged In and id is " + g.getGuestID();
		else {
			return "Invalid Guest";
		}
	}

	@PostMapping("/resort/{id}")
	public Resort bookResort(@RequestBody Resort r, @PathVariable long id) {
		bookingService.bookResort(r, id);
		return r;
	}

	@PostMapping("/dinning/{id}")
	public Dinning bookDinning(@RequestBody Dinning d, @PathVariable long id) {
		bookingService.bookResort(d, id);
		return d;
	}
}