package com.housing.app.service;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import com.housing.app.dto.ListingRequest;
import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.model.Listing;
import org.springframework.data.domain.Page;

public interface ListingService {

	public List<Listing> findAll();

	public Listing findById(long id);

	public Listing create(ListingRequest listingRequest, Principal principal);

	public Page<Listing> search(ListingSearchRequest request);

	public Listing update(Long id, @Valid ListingRequest request);

}
