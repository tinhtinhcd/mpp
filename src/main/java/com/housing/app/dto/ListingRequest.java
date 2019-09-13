package com.housing.app.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ListingRequest {

	@NotEmpty(message = "title should not be empty")
	private String title;
	private double latitude;
	private double longitude;
	@NotEmpty(message = "address should not be empty")
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
	private long[] utilities;
	private List<String> images;
	private boolean isIncludeUtilities;
}
