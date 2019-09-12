package com.housing.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.housing.app.model.Listing;

@Repository
public interface ListingRepository extends BaseRepository<Listing, Long> {


    @Query(
            value = "select l.* from \n" +
                    "listing l " +
                    "where earth_distance(ll_to_earth(l.latitude, l.longitude ) , ll_to_earth(:lat, :lng) ) < :radius \n" +
                    "and l.price <= :price \n" +
                    "and l.area <= :area",
            countQuery = "select count(*) from \n" +
                    "listing l " +
                    "where earth_distance(ll_to_earth(l.latitude, l.longitude ) , ll_to_earth(:lat, :lng) ) < :radius \n" +
                    "and l.price <= :price \n" +
                    "and l.area <= :area",
            nativeQuery = true)
    Page<Listing> searchListing(@Param("lat") double lat, @Param("lng") double lng,
                                @Param("radius") int radius, @Param("price") int price,
                                @Param("area") int area, Pageable pageable);
}
