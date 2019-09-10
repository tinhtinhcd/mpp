package com.housing.app.model;


import javax.persistence.*;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "listing", schema = "`listing`")
public class Listing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
