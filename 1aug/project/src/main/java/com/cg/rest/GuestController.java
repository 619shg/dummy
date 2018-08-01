package com.cg.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
public class GuestController {

	@Autowired
	private GuestService guestService;

	@Autowired
	private BookingService bookingService;

	@PostMapping("/register")
	public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
		Guest checkedGuest = guestService.registerGuest(guest);
		if (checkedGuest != null)
			return new ResponseEntity<Guest>(checkedGuest, HttpStatus.CREATED);
		else
			return new ResponseEntity<Guest>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public String processGuest(@RequestBody Guest guest) {
		long validGuest;
		validGuest = guestService.validateGuest(guest.getEmail(), guest.getPassword());
		if (validGuest > 0)
			return "Guest Validated and Logged In with GuestID: " + validGuest;
		else {
			return "Invalid Guest";
		}
	}

	@GetMapping("/view/{guestID}")
	public List<Object> viewItenarary(@PathVariable long guestID) {
		List<Object> list = new ArrayList<>();

		List<Resort> resort = bookingService.getAllResortDetails(guestID);
		List<Dinning> dinning = bookingService.getAllDiningDetails(guestID);

		list.add(resort);
		list.add(dinning);
		return list;

	}
}