package com.cg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;
import com.cg.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

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
