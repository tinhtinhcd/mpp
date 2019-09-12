package com.housing.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housing.app.model.Listing;
import com.housing.app.repo.ListingRepository;
import com.housing.app.service.ListingService;

@Service
public class ListingServiceImpl implements ListingService {

	private final ListingRepository listingRepository;

	@Autowired
	public ListingServiceImpl(ListingRepository listingRepository) {
		this.listingRepository = listingRepository;
	}
	
	@Override
	public List<Listing> findAll() {
		return listingRepository.findAll();
	}

	@Override
	public Listing create(Listing listing) {
		listingRepository.saveAndFlush(listing);
		return listing;
	}

}
