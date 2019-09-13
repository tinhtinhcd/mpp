package com.housing.app.service;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import com.housing.app.dto.ListingRequest;
import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.model.Listing;
import com.housing.app.model.ListingImage;
import com.housing.app.model.Utility;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ListingService {

	public List<Listing> findAll();

	public Listing findById(long id);

	public Listing create(ListingRequest listingRequest, Principal principal);

	public Page<Listing> search(ListingSearchRequest request);

	public Listing update(Long id, @Valid ListingRequest request);

	public ListingImage saveImage(Long listingId, MultipartFile image);
	
	public List<Utility> getUtitlities();
}
