package com.housing.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utility", schema = "`listing`")
@Getter
@Setter
public class Ultility extends AuditModel {
	private String description;
	@OneToMany(mappedBy = "listing")
	private List<ListingUtilities> listingUtilities;
}
