package com.housing.app.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.housing.app.common.AppConstant;
import com.housing.app.dto.*;
import com.housing.app.mapper.ListingMapper;
import com.housing.app.mapper.ListingTypeMapper;
import com.housing.app.model.ListingType;
import com.housing.app.model.Utility;
import com.housing.app.repo.ListingTypeRepository;
import com.housing.app.service.ListingTypeService;
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

import com.housing.app.model.Listing;
import com.housing.app.service.ListingService;
import com.housing.app.service.UserService;
import com.housing.app.util.RequestUtil;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/listing", produces = { "application/json" })
public class ListingController {

	@Autowired
	private ListingService listingService;

	@Autowired
	ListingTypeService listingTypeService;

	@Autowired
	ListingTypeRepository listingTypeRepository;

	@Autowired
	private UserService userService;

	private final ListingMapper mapper = Mappers.getMapper(ListingMapper.class);

	private final ListingTypeMapper listingTypeMapper = Mappers.getMapper(ListingTypeMapper.class);

	@GetMapping()
	public ResponseEntity<List<ListingDto>> getAll() {
		List<Listing> listings = listingService.findAll();
		return new ResponseEntity<>(listings.stream().map(p -> mapper.toListingDto(p)).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Listing> create(@Valid @RequestBody ListingRequest request, Principal principal) {
		Listing listing = mapper.toPersistent(request);
		listing.setUser(userService.findUserByEmail(principal.getName()));
		return new ResponseEntity<>(listingService.create(request, principal), HttpStatus.OK);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<ListingResultDto> search(@RequestBody ListingSearchRequest request) {
		return new ResponseEntity<>(mapper.toListingResultDto(listingService.search(request)), HttpStatus.OK);
	}

	@GetMapping(value = "/similarListing")
	public ResponseEntity<ListingResultDto> similarListing(@RequestParam(name = "listingId") Long listingId) {
		Listing currentListing = listingService.findById(listingId);
		ListingSearchRequest request = new ListingSearchRequest();
		request.setLatitude(currentListing.getLatitude());
		request.setLongitude(currentListing.getLongitude());
		request.setArea(currentListing.getArea());
		request.setPrice(currentListing.getPrice());
		request.setNumBath(currentListing.getNumBath());
		request.setNumBed(currentListing.getNumBed());
		request.setRadius(AppConstant.DEFAULT_RADIUS);
		request.setListType(currentListing.getListType());
		request.setPage(0);
		request.setSize(AppConstant.DEFAULT_PAGE_SIZE);
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

	@PostMapping(value = "/uploadImage")
	public ResponseEntity<ListingImageDto> uploadImage(@RequestParam(value = "file") MultipartFile image,
			@RequestParam(name = "listingId") Long listingId) {
		RequestUtil.validateImage(image.getContentType());
		return new ResponseEntity<>(mapper.toListingImageDto(listingService.saveImage(listingId, image)),
				HttpStatus.OK);
	}

	@GetMapping("/listTypes")
	public ResponseEntity<List<ListingTypeDto>> getAllListingType() {
		List<ListingType> listings = listingTypeService.findAll();
		return new ResponseEntity<>(listings.stream().map(p -> listingTypeMapper.toDto(p)).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping(value = "listUtilities")
	public ResponseEntity<List<Utility>> getAllUtitlites() {
		return new ResponseEntity<>(listingService.getUtitlities(), HttpStatus.OK);
	}
}
