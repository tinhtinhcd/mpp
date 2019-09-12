package com.housing.app.dto;

import java.util.Date;

import lombok.Data;

@Data
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
	private long description;
	private int listType;
	private int[] utilities;
	private int userDto;
}
