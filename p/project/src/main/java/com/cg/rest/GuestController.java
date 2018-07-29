package com.cg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Guest;
import com.cg.service.GuestService;

@RestController
@RequestMapping("/guest")
public class GuestController {

	@Autowired
	private GuestService guestService;

	@PostMapping("/register")
	public String createGuest(@RequestBody Guest guest) {
		long registeredGuestID = 0;
		registeredGuestID=guestService.registerGuest(guest);
		if(registeredGuestID>0)
			return "Guest already registered with guestID:	"+registeredGuestID ;
		else
			return "New Guest registered with guestID:"+registeredGuestID;
	}

	@PostMapping("/login")
	public String processGuest(@RequestBody Guest g) {
		long validGuest;
		validGuest = guestService.validateGuest(g.getEmail(), g.getPassword());
		if (validGuest > 0)
			return "Guest Validated and Logged In with GuestID: " + validGuest;
		else {
			return "No Guest found with given credentials. Please check credentials or Register New Guest.";
		}
	}

}