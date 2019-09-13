package com.housing.app.dto;

import lombok.Data;
@Data
public class ListingSearchRequest {

    private long latitude;
    private long longitude;
    private int price;
    private int area;
    private int radius;
    private int numBed;
    private int numBath;
    private int listType;
    private String status;

    // paging attribute
    private int page;
    private int size;
    
    
    
    
    
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getNumBed() {
		return numBed;
	}
	public void setNumBed(int numBed) {
		this.numBed = numBed;
	}
	public int getNumBath() {
		return numBath;
	}
	public void setNumBath(int numBath) {
		this.numBath = numBath;
	}
	public int getListType() {
		return listType;
	}
	public void setListType(int listType) {
		this.listType = listType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
    
    
    
    
    
    

}
