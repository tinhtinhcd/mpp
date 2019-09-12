package com.housing.app.service;

import java.util.List;

import com.housing.app.dto.ListingResultDto;
import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.model.Listing;
import org.springframework.data.domain.Page;

public interface ListingService {
	List<Listing> findAll();

	Page<Listing> search(ListingSearchRequest request);

}
