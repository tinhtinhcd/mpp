package com.housing.app.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.housing.app.model.ListingUtilities;

import lombok.Data;

@Data
public class ListingDto {
    private String title;
    private double latitude;
    private double longitude;
    private String address;
    private BigDecimal price;
    private Date availableFrom;
    private String status;
    private int minimumLease;
    private int numBed;
    private int numBath;
    private int area;
    private String description;
    private int listType;
    private List<ListingUtilities> listingUtilities;
    private List<ListingImageDto> images;
    private boolean isIncludeUtilities;
}
