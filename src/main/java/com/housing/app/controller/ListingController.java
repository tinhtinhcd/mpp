package com.housing.app.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.housing.app.dto.ListingDto;
import com.housing.app.dto.ListingRequest;
import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.mapper.ListingMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housing.app.dto.ListingResultDto;
import com.housing.app.model.Listing;
import com.housing.app.service.ListingService;

@RestController
@RequestMapping(value = "/listing", produces = { "application/json" })
public class ListingController {

	@Autowired
	ListingService listingService;
	
	private final ListingMapper mapper = Mappers.getMapper(ListingMapper.class);

	@GetMapping(value = "/all")
	public ResponseEntity<List<ListingDto>> getAll() {
		List<Listing> listings = listingService.findAll();
		return new ResponseEntity<>(listings.stream().map(p -> mapper.toListingDto(p)).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Listing> create(@Valid @RequestBody ListingRequest request, Principal principal) {
		return new ResponseEntity<>(listingService.create(request, principal), HttpStatus.OK);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<ListingResultDto> search(@RequestBody ListingSearchRequest request) {
		return new ResponseEntity<>(mapper.toListingResultDto(listingService.search(request)), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ListingDto> view(@PathVariable Long id) {
		return new ResponseEntity<>(mapper.toListingDto(listingService.findById(id)), HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<ListingDto> update(@Valid @RequestBody ListingRequest request, @RequestParam Long id) {
		return new ResponseEntity<>(mapper.toListingDto(listingService.update(id, request)), HttpStatus.OK);
	}

}
