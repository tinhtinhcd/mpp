package com.housing.app.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingDto {
    private String title;
    private long latitude;
    private long longitude;
    private String address;
    private double price;
    private Date availableFrom;
    private String status;
    private int minimumLease;
    private int numBed;
    private int numBath;
    private int area;
    private String description;
    private int listType;
    private List<ListingImageDto> images;
}
