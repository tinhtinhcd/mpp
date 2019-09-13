package com.housing.app.dto;

import lombok.Data;
@Data
public class ListingSearchRequest {

    private double latitude;
    private double longitude;
    private int price;
    private int area;
    private int radius;

    // paging attribute
    private int page;
    private int size;

}
