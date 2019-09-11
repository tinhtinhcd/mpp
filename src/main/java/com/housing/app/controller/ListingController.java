package com.housing.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housing.app.model.Listing;
import com.housing.app.service.ListingService;

@RestController
@RequestMapping(value = "/listing", produces = { "application/json", "application/xml" })
public class ListingController {

	@Autowired
	ListingService listingService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<Listing>> getAll() {
		Assert.notNull(listingService.findAll(), "Body must not be null");
		return new ResponseEntity<>(listingService.findAll(), HttpStatus.OK);
	}
}
