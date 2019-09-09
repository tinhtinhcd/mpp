package com.housing.app.dao;

import java.util.List;
import java.util.Optional;

import com.housing.app.idao.IListingDAO;
import com.housing.app.models.ListingModel;

public class ListingDAO implements IListingDAO{

	@Override
	public Optional<ListingModel> get(long id) {
		return null;
	}

	@Override
	public List<ListingModel> getAll() {
		return null;
	}

	@Override
	public void save(ListingModel t) {
		
	}

	@Override
	public void update(ListingModel t, String[] params) {
		
	}

	@Override
	public void delete(ListingModel t) {
		
	}

}
