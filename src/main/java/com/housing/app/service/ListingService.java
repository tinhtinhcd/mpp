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

	List<Listing> findLatestListing(int limit);

	Listing findById(long id);

	void deleteListing(long id);

	Listing create(ListingRequest listingRequest, Principal principal);

	Page<Listing> search(ListingSearchRequest request);

	Listing update(Long id, @Valid ListingRequest request);

	Listing update(Listing listing);

	ListingImage saveImage(Long listingId, MultipartFile image);
	
	List<Utility> getUtitlities();


}
