package com.housing.app.service;

import java.util.List;

import com.housing.app.model.ListingType;

public interface ListingTypeService {
	public List<ListingType> findAll();
	public ListingType save(ListingType listing);
}
