package com.housing.app.dto;

import lombok.Data;
@Data
public class ListingSearchRequest {

    private long latitude;
    private long longitude;
    private int price;
    private int area;
    private int radius;

    // paging attribute
    private int page;
    private int size;

}
