package com.housing.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "listing_utility", schema = "`listing`")
public class ListingUtilities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

	@ManyToOne
	@JoinColumn(name = "listing_id")
	Listing listing;

}
