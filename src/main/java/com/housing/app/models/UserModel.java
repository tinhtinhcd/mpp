package com.housing.app.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")

public class UserModel extends AuditModel {

	@NotBlank
	String username;
	@NotBlank
	String password;
	
}
