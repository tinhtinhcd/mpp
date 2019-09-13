package com.housing.app.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ListingDto {
    private String title;
    private double latitude;
    private double longitude;
    private String address;
    private int price;
    private Date availableFrom;
    private String status;
    private int minimumLease;
    private int numBed;
    private int numBath;
    private int area;
    private String description;
    private int listType;
    private int[] utilities;
    private List<ListingImageDto> images;
}
