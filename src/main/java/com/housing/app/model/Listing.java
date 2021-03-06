package com.housing.app.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

/**
 * The persistent class for the user database table.
 */

@Entity
@Table(name = "listing", schema = "`listing`")
@Data
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified", nullable = false)
    @LastModifiedDate
    private Date updatedAt;
    private String title;
    private double latitude;
    private double longitude;
    private String address;
    private int price;
    @Column(name = "available_from")
    private Date availableFrom;
    @Enumerated(EnumType.STRING)
    private ListingStatus status;
    @Column(name = "minimum_lease")
    private int minimumLease;
    @Column(name = "num_bed")
    private int numBed;
    @Column(name = "num_bath")
    private int numBath;
    private int area;
    private String description;
    @Column(name = "list_type")
    private int listType;
    @OneToMany(
    		mappedBy = "listing",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ListingUtilities> listingUtilities;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User user;
    @OneToMany(
            mappedBy = "listing",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ListingImage> listingImages;
    private boolean furnished;
    private boolean isIncludeUtilities;

    public void removeImage(ListingImage image) {
        listingImages.remove(image);
        image.setListing(null);
    }
}
