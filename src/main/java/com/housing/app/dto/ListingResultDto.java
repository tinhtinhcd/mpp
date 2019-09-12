package com.housing.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListingResultDto {

    private List<ListingDto> listings;
    private int pages;
    private long results;
}
