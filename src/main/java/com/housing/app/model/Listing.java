package com.housing.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "listing", schema = "`listing`")
@Getter
@Setter
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
	private long description;
	@Column(name = "list_type")
	private int listType;
	@OneToMany(mappedBy = "ultility")
	private List<ListingUtilities> listingUtilities;
	@ManyToOne
	private User user;

}
