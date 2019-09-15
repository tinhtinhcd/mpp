package com.housing.app.dto;

import com.housing.app.model.ListingStatus;
import lombok.Data;

@Data
public class ListingStatusRequest {

    private ListingStatus status;
    private long listingId;
}
