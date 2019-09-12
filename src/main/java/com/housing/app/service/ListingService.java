package com.housing.app.service;

import java.util.List;

import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.model.Listing;
import org.springframework.data.domain.Page;

public interface ListingService {

	public List<Listing> findAll();
	public Listing create(Listing listing);
	Page<Listing> search(ListingSearchRequest request);

}
