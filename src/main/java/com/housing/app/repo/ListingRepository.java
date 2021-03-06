
package com.housing.app.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.housing.app.model.Listing;

@Repository
public interface ListingRepository extends BaseRepository<Listing, Long> {

	@Query(
            value = "select l.* from " +
                    "listing l " +
                    "where earth_distance(ll_to_earth(l.latitude, l.longitude ) , ll_to_earth(:lat, :lng) ) < :radius " +
                    "and (:price = 0 or l.price <= :price ) " +
                    "and (:area = 0 or l.area <= :area ) " +
                    "and (:num_bed = 0 or l.num_bed <= :num_bed ) "+
                    "and (:num_bath = 0 or l.num_bath <= :num_bath ) "+
                    "and (:list_type = 0 or l.list_type = :list_type) ",
            countQuery = "select count(*) from " +
                    "listing l " +
                    "where earth_distance(ll_to_earth(l.latitude, l.longitude ) , ll_to_earth(:lat, :lng) ) < :radius " +
                    "and (:price = 0 or l.price <= :price ) " +
                    "and (:area = 0 or l.area <= :area ) " +
                    "and (:num_bed = 0 or l.num_bed <= :num_bed ) "+
                    "and (:num_bath = 0 or l.num_bath <= :num_bath ) "+
                    "and (:list_type = 0 or l.list_type = :list_type) " ,
            nativeQuery = true)
    Page<Listing> searchListing(@Param("lat") double lat, @Param("lng") double lng,
                                @Param("radius") int radius, @Param("price") int price,
                                @Param("area") int area,@Param("num_bed") int numBed,@Param("num_bath") int numBath,
                                @Param("list_type") int listType, Pageable pageable);

    @Query(
            value = "select t.* from listing t \n" +
                    "left join listing_image i on t.id = i.listing_id \n" +
                    "where length(t.description) > 10\n" +
                    "group by t.id \n" +
                    "having count(i.id) > 0 \n" +
                    "order by t.last_modified desc \n" +
                    "limit :limit",
            nativeQuery = true)
    List<Listing> findLatestListing(@Param("limit") int limit);
}

