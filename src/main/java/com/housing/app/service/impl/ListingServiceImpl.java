package com.housing.app.service.impl;

import java.util.List;
import java.util.Optional;

import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.model.ListingImage;
import com.housing.app.repo.ListingImageRepository;
import com.housing.app.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.housing.app.model.Listing;
import com.housing.app.repo.ListingRepository;
import com.housing.app.service.ListingService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ListingServiceImpl implements ListingService {

	private final ListingRepository listingRepository;

	private final ListingImageRepository listingImageRepository;

	private final S3Service s3Service;

	@Autowired
	public ListingServiceImpl(ListingRepository listingRepository,
							  ListingImageRepository listingImageRepository,
							  S3Service s3Service) {
		this.listingRepository = listingRepository;
		this.listingImageRepository = listingImageRepository;
		this.s3Service = s3Service;
	}
	
	@Override
	public List<Listing> findAll() {
		return listingRepository.findAll();
	}

	@Override
	public Listing create(Listing listing) {
		return listingRepository.saveAndFlush(listing);
	}

	public Page<Listing> search(ListingSearchRequest request) {
		return listingRepository.searchListing(request.getLatitude(), request.getLongitude(), request.getRadius()*1000,
				request.getPrice(), request.getArea(),request.getNumBed(),request.getNumBath(),request.getListType(),request.getStatus(),
				PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, "last_modified"));
	}

	@Override
	public Listing findById(long id) {
		return listingRepository.getOne(id);
	}

	@Override
	public ListingImage saveImage(Long listingId, MultipartFile image) {
		Listing listing = findById(listingId);
		String imageName = listing.getId() +"/"+ image.getOriginalFilename();
		String imageUrl = s3Service.uploadFile(imageName, image);
		ListingImage listingImage = new ListingImage();
		listingImage.setUrl(imageUrl);
		listingImage.setListing(listing);
		return listingImageRepository.saveAndFlush(listingImage);
	}
}
