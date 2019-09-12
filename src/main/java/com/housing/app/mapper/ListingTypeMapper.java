package com.housing.app.mapper;

import org.mapstruct.Mapper;

import com.housing.app.dto.ListingTypeDto;
import com.housing.app.model.ListingType;

@Mapper
public interface ListingTypeMapper {
	ListingType toPersistent(ListingTypeDto listingTypeDto);
	ListingTypeDto toDto(ListingType listingType);
}
