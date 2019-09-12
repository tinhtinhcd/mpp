package com.housing.app.repo;

import com.housing.app.model.Listing;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingImageRepository extends BaseRepository<Listing, Long> {
}
