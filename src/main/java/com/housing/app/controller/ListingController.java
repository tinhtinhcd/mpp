package com.housing.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housing.app.dto.ListingDto;
import com.housing.app.mapper.ListingMapper;
import com.housing.app.model.Listing;
import com.housing.app.service.ListingService;
import com.housing.app.service.UserService;
import com.housing.app.util.RequestUtil;

@RestController
@RequestMapping(value = "/listing", produces = { "application/json" })
public class ListingController {

	@Autowired
	ListingService listingService;

	@Autowired
	UserService userService;

	ListingMapper mapper = Mappers.getMapper(ListingMapper.class);

	@GetMapping(value = "/all")
	public ResponseEntity<List<Listing>> getAll() {
		Assert.notNull(listingService.findAll(), "Body must not be null");
		return new ResponseEntity<>(listingService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<ListingDto> create(@Valid @RequestBody ListingDto listingDto, BindingResult result) {
		RequestUtil.validateRequest(result);
		Listing listing = mapper.toPersistent(listingDto);
		listing.setUser(userService.findById(listingDto.getUserDto()));
		listingService.create(listing);
		return new ResponseEntity<>(listingDto, HttpStatus.OK);
	}
}
