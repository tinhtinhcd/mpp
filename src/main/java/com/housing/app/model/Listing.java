package com.housing.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

/**
 * The persistent class for the user database table.
 */
@Data
@Entity
@Table(name = "listing", schema = "`listing`")
public class Listing extends AuditModel {

	private String title;
	private long latitude;
	private long longitude;
	private String address;
	private double price;
	@Column(name = "available_from")
	private Date availableFrom;
	private String status;
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
	@ManyToOne
	@JoinColumn(name = "created_by")
	private User user;
	@OneToMany(mappedBy = "listing")
	private List<ListingImage> listingImages;

}
