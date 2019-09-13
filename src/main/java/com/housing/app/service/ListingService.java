package com.housing.app.service;

import java.util.List;
import java.util.Optional;

import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.model.Listing;
import com.housing.app.model.ListingImage;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ListingService {
	List<Listing> findAll();
	Listing findById(long id);
	Listing create(Listing listing);
	Page<Listing> search(ListingSearchRequest request);
	ListingImage saveImage(Long listingId, MultipartFile image);
}
