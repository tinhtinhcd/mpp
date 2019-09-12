package com.housing.app.controller;

import java.util.List;

import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.mapper.ListingMapper;
import com.housing.app.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housing.app.model.Listing;
import com.housing.app.service.ListingService;

@RestController
@RequestMapping(value = "/listing", produces = { "application/json", "application/xml" })
public class ListingController {

	@Autowired
	ListingService listingService;

	private final ListingMapper listingMapper = Mappers.getMapper(ListingMapper.class);

	@GetMapping(value = "/all")
	public ResponseEntity<List<Listing>> getAll() {
		return new ResponseEntity<>(listingService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<Listing>> search(@RequestBody ListingSearchRequest request) {
		return new ResponseEntity(listingMapper.toListingResultDto(listingService.search(request)), HttpStatus.OK);
	}
}
