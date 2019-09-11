package com.housing.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housing.app.model.Listing;
import com.housing.app.repo.ListingRepository;
import com.housing.app.service.ListingService;

@Service
public class ListingServiceImpl implements ListingService {

	@Autowired
	ListingRepository listingRepository;

	@Override
	public List<Listing> findAll() {
		return listingRepository.findAll();
	}

}
