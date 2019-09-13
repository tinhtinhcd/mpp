package com.housing.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housing.app.dto.ListingTypeDto;
import com.housing.app.mapper.ListingTypeMapper;
import com.housing.app.model.ListingType;
import com.housing.app.repo.ListingTypeRepository;
import com.housing.app.service.ListingTypeService;
import com.housing.app.util.RequestUtil;

@RestController
@RequestMapping(value = "/listing/type", produces = { "application/json" })
public class ListingTypeController {

	@Autowired
	ListingTypeService listingTypeService;

	@Autowired
	ListingTypeRepository listingTypeRepository;

	private final ListingTypeMapper mapper = Mappers.getMapper(ListingTypeMapper.class);

	@GetMapping()
	public ResponseEntity<List<ListingTypeDto>> getAll() {
		List<ListingType> listings = listingTypeService.findAll();
		return new ResponseEntity<>(listings.stream().map(p -> mapper.toDto(p)).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<ListingTypeDto> create(@Valid @RequestBody ListingTypeDto listingTypeDto,
			BindingResult result) {
		RequestUtil.validateRequest(result);
		ListingType listingType = mapper.toPersistent(listingTypeDto);
		listingTypeService.save(listingType);
		return new ResponseEntity<>(listingTypeDto, HttpStatus.OK);
	}

	@PostMapping(value = "/createList")
	public ResponseEntity<List<ListingType>> testCreateList(@Valid @RequestBody List<String> listingTypeDto,
			BindingResult result) {
		RequestUtil.validateRequest(result);

		List<ListingType> listingTypes = listingTypeDto.stream().map(s -> {
			ListingType listing = new ListingType();
			listing.setDescription(s);
			return listing;
		}).collect(Collectors.toList());

		listingTypeService.saveAll(listingTypes);
		return new ResponseEntity<>(listingTypes, HttpStatus.OK);
	}
}
