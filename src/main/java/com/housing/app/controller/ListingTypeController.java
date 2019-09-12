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

	@GetMapping(value = "/all")
	public ResponseEntity<List<ListingType>> getAll() {
		Assert.notNull(listingTypeService.findAll(), "Body must not be null");
		return new ResponseEntity<>(listingTypeService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<ListingTypeDto> create(@Valid @RequestBody ListingTypeDto listingTypeDto,
			BindingResult result) {
		RequestUtil.validateRequest(result);
		ListingType listingType = mapper.toPersistent(listingTypeDto);
		listingTypeService.save(listingType);
		return new ResponseEntity<>(listingTypeDto, HttpStatus.OK);
	}

}
