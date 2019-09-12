package com.housing.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "listing_utility", schema = "`listing`")
public class ListingUtilities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

	@Column(name = "listing_id")
	int listing;

	@ManyToOne
	@JoinColumn(name = "utility_id")
	Ultility ultility;

}
