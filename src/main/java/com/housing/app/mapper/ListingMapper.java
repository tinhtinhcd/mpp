package com.housing.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.housing.app.dto.ListingDto;
import com.housing.app.dto.ListingRequest;
import com.housing.app.dto.ListingResultDto;
import com.housing.app.model.Listing;
import com.housing.app.dto.ListingImageDto;
import com.housing.app.model.ListingImage;
import org.springframework.data.domain.Page;

@Mapper
public interface ListingMapper {

	@Mapping(source = "listingImages", target = "images")
	@Mapping(target = "name", expression = "java(listing.getUser().getFirstName())")
	@Mapping(target = "phone", expression = "java(listing.getUser().getPhone())")
	ListingDto toListingDto(Listing listing);

	List<ListingDto> toListingDtos(List<Listing> listings);

	ListingImageDto toListingImageDto(ListingImage image);

	default ListingResultDto toListingResultDto(Page<Listing> listingPage) {
		ListingResultDto listingResultDto = new ListingResultDto();
		listingResultDto.setPages(listingPage.getTotalPages());
		listingResultDto.setResults(listingPage.getTotalElements());
		List<ListingDto> listingDtos = listingPage.getContent().stream().map(this::toListingDto)
				.collect(Collectors.toList());
		listingResultDto.setListings(listingDtos);
		return listingResultDto;
	}

	@Mapping(target = "availableFrom", expression = "java(com.housing.app.util.LocalDateUtil.convertStringToDate(listingRequest.getAvailableFrom()))")
	Listing toPersistent(ListingRequest listingRequest);
}
