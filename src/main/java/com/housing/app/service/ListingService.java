package com.housing.app.service;

import java.util.List;
import java.util.Optional;

import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.model.Listing;
import org.springframework.data.domain.Page;

public interface ListingService {
	List<Listing> findAll();
	Listing findById(long id);
	public Listing create(Listing listing);
	Page<Listing> search(ListingSearchRequest request);

}
