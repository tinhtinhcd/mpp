package com.housing.app.model;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "users", schema = "`listing`")
@Getter
@Setter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "active")
    private boolean active;

    @Column(name = "date_created")
    private Timestamp dateCreated;

    @Column(name = "last_modified")
    private Timestamp lastModified;

    @OneToMany(mappedBy = "id")
    private List<Listing> listings;
    
}
