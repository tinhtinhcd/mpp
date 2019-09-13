package com.housing.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.housing.app.dto.ListingDto;
import com.housing.app.dto.ListingRequest;
import com.housing.app.dto.ListingResultDto;
import com.housing.app.model.Listing;
import org.springframework.data.domain.Page;

@Mapper
public interface ListingMapper {

	@Mapping(source = "listingImages", target = "images")
	ListingDto toListingDto(Listing listing);

	Listing toPersistent(ListingDto listingDto);

	default ListingResultDto toListingResultDto(Page<Listing> listingPage) {
		ListingResultDto listingResultDto = new ListingResultDto();
		listingResultDto.setPages(listingPage.getTotalPages());
		listingResultDto.setResults(listingPage.getTotalElements());
		List<ListingDto> listingDtos = listingPage.getContent().stream().map(this::toListingDto)
				.collect(Collectors.toList());
		listingResultDto.setListings(listingDtos);
		return listingResultDto;
	}

	Listing toPersistent(ListingRequest listingRequest);
}
