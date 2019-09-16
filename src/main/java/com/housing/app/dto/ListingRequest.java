package com.housing.app.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ListingRequest {

	private long id;
	@NotEmpty(message = "title should not be empty")
	private String title;
	private double latitude;
	private double longitude;
	@NotEmpty(message = "address should not be empty")
	private String address;
	private int price;
	private String availableFrom;
	private int minimumLease;
	private int numBed;
	private int numBath;
	private int area;
	@NotEmpty(message = "description should not be empty")
	private String description;
	private long listType;
	private long[] utilities;
	private boolean isIncludeUtilities;
	private boolean furnished;
}
