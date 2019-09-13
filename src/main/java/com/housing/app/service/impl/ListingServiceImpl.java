package com.housing.app.service.impl;

import java.util.List;
import java.util.Optional;

import com.housing.app.dto.ListingSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	public Page<Listing> search(ListingSearchRequest request) {
		return listingRepository.searchListing(request.getLatitude(), request.getLongitude(), request.getRadius(),
				request.getPrice(), request.getArea(),
				PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, "last_modified"));
	}

	@Override
	public Listing findById(long id) {
		return listingRepository.getOne(id);
	}
}
