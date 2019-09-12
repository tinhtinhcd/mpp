package com.housing.app.mapper;

import org.mapstruct.Mapper;

import com.housing.app.dto.ListingDto;
import com.housing.app.model.Listing;

@Mapper
public interface ListingMapper {
	ListingDto toDto(Listing listing);
	Listing toPersistent(ListingDto listingDto);
}
