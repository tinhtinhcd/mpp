package com.housing.app.mapper;

import com.housing.app.dto.ListingDto;
import com.housing.app.dto.ListingResultDto;
import com.housing.app.model.Listing;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ListingMapper {

    ListingDto toListingDto(Listing listing);

    default ListingResultDto toListingResultDto(Page<Listing> listingPage) {
        ListingResultDto listingResultDto = new ListingResultDto();
        listingResultDto.setPages(listingPage.getTotalPages());
        listingResultDto.setResults(listingPage.getTotalElements());
        List<ListingDto> listingDtos = listingPage.getContent().stream().map(this::toListingDto).collect(Collectors.toList());
        listingResultDto.setListings(listingDtos);
        return listingResultDto;
    }
}
