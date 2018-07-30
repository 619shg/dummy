package com.cg.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;
import com.cg.service.BookingService;
import com.cg.service.GuestService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private GuestService guestService;

	@PostMapping("/resort/{guestID}")
	public String bookResort(@RequestBody Resort resort, @PathVariable long guestID) {
		long validGuestID;
		validGuestID = guestService.validateGuestID(guestID);
		if (validGuestID > 0) {
			long bookingResortId = bookingService.bookResort(resort, guestID);
			return "Guest with guestID: " + guestID + "Booked Resort with resort booking id: " + bookingResortId;
		} else {
			return "Guest not registered.";
		}
	}

	@GetMapping("/resort/{guestID}")
	public List<Resort> getbookResort(@PathVariable long guestID) {
		return bookingService.getAllResortDetails(guestID);
	}

	@PutMapping("/update/resort/{resortBookingId}")
	public Resort updateBookResort(@RequestBody Resort resort, @PathVariable long resortBookingId) {
		bookingService.updateBookResort(resort, resortBookingId);
		return resort;
	}

	@PostMapping("/dinning/{dinningBookingId}")
	public Dinning bookDinning(@RequestBody Dinning dinning, @PathVariable long dinningBookingId) {
		bookingService.bookDinning(dinning, dinningBookingId);
		return dinning;
	}

	@PutMapping("/update/dinning/{dinningBookingId}")
	public Dinning updateBookDinning(@RequestBody Dinning dinning, @PathVariable long dinningBookingId) {
		bookingService.updateBookDinning(dinning, dinningBookingId);
		return dinning;
	}

}
