package com.housing.app.service.impl;

import java.util.List;

import com.housing.app.dto.ListingResultDto;
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

	@Autowired
	ListingRepository listingRepository;

	@Override
	public List<Listing> findAll() {
		return listingRepository.findAll();
	}

	@Override
	public Page<Listing> search(ListingSearchRequest request) {
		return listingRepository.searchListing(request.getLatitude(), request.getLongitude(), request.getRadius(),
				request.getPrice(), request.getArea(),request.getNumBed(),request.getNumBath(),request.getListType(),request.getStatus(),
				PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, "last_modified"));
	}
}
