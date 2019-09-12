package com.housing.app.mapper;

import com.housing.app.dto.ListingDto;
import com.housing.app.dto.ListingImageDto;
import com.housing.app.dto.ListingResultDto;
import com.housing.app.model.Listing;
import com.housing.app.model.ListingImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ListingMapper {

    @Mapping (source = "listingImages" , target = "images" )
    ListingDto toListingDto(Listing listing);

    ListingImageDto toListingImageDto (ListingImage image);

    default ListingResultDto toListingResultDto(Page<Listing> listingPage) {
        ListingResultDto listingResultDto = new ListingResultDto();
        listingResultDto.setPages(listingPage.getTotalPages());
        listingResultDto.setResults(listingPage.getTotalElements());
        List<ListingDto> listingDtos = listingPage.getContent().stream().map(this::toListingDto).collect(Collectors.toList());
        listingResultDto.setListings(listingDtos);
        return listingResultDto;
    }
}
