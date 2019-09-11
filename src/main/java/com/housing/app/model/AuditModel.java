package com.housing.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created_at", "updated_At" }, allowGetters = true)
@Getter
public abstract class AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = false, updatable = false)
	@CreatedDate
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", nullable = false)
	@LastModifiedDate
	private Date updatedAt;

	// Getters and Setters (Omitted for brevity)
}
