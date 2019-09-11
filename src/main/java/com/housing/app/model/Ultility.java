package com.housing.app.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utility", schema = "`listing`")
@Getter
@Setter
public class Ultility extends AuditModel {
	private String description;
	@ManyToOne
	private Listing listing;
}
