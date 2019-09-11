package com.housing.app.repo;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.housing.app.model.Listing;

@Repository
public interface ListingRepository extends BaseRepository<Listing, Long> {

	@Query("Select DISTINCT l FROM Listing l where price > ?1 and price < ?2")
	public List<Listing> getListingInPriceRange(double from, double to, Pageable pageable);
}
