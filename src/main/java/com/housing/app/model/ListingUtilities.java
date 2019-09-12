package com.housing.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ListingUtilities {

	@Id
    Long id;

	@ManyToOne
	@JoinColumn(name = "listing_id")
	Listing listing;

	@ManyToOne
	@JoinColumn(name = "utility_id")
	Ultility ultility;

}
