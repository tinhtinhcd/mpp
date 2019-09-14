package com.housing.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "listing_utility", schema = "`listing`")
public class ListingUtilities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@ManyToOne
	@JoinColumn(name = "listing_id")
	Listing listing;

	@ManyToOne
	@JoinColumn(name = "utility_id")
	Utility utility;

	public ListingUtilities(Listing listing, Utility utility) {
		super();
		this.listing = listing;
		this.utility = utility;
	}
}
