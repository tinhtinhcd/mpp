package com.housing.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housing.app.model.ListingType;
import com.housing.app.repo.ListingTypeRepository;
import com.housing.app.service.ListingTypeService;

@Service
public class ListingTypeServiceImpl implements ListingTypeService {

	@Autowired
	ListingTypeRepository listingTypeRepository;

	@Override
	public List<ListingType> findAll() {
		return listingTypeRepository.findAll();
	}

	@Override
	public ListingType save(ListingType listingType) {
		return listingTypeRepository.save(listingType);
	}

<<<<<<< HEAD
=======
	@Override
	public List<ListingType> saveAll(List<ListingType> listingTypes) {
		return listingTypeRepository.saveAll(listingTypes);
	}

>>>>>>> develop
}
