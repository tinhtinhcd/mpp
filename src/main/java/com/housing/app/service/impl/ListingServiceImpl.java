package com.housing.app.service.impl;

import java.security.Principal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.housing.app.model.*;
import org.mapstruct.factory.Mappers;

import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.exception.EntityNotFoundException;
import com.housing.app.exception.RequestBindingException;
import com.housing.app.repo.ListingImageRepository;
import com.housing.app.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.housing.app.dto.ListingRequest;
import com.housing.app.mapper.ListingMapper;
import com.housing.app.repo.ListingRepository;
import com.housing.app.repo.ListingTypeRepository;
import com.housing.app.repo.ListingUtilityRepository;
import com.housing.app.repo.UtilitiesRepository;
import com.housing.app.service.ListingService;
import com.housing.app.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ListingServiceImpl implements ListingService {

	private final ListingMapper mapper = Mappers.getMapper(ListingMapper.class);

	@Autowired
	private final ListingRepository listingRepository;

	private final ListingImageRepository listingImageRepository;

	private final S3Service s3Service;

	@Autowired
	UserService userService;

	@Autowired
	ListingUtilityRepository listingUtilityRepository;

	@Autowired
	UtilitiesRepository utilitiesRepository;

	@Autowired
	public ListingServiceImpl(ListingRepository listingRepository, ListingImageRepository listingImageRepository,
			S3Service s3Service) {
		this.listingRepository = listingRepository;
		this.listingImageRepository = listingImageRepository;
		this.s3Service = s3Service;
	}

	public Page<Listing> search(ListingSearchRequest request) {
		return listingRepository.searchListing(request.getLatitude(), request.getLongitude(),
				request.getRadius() * 1000, request.getPrice(), request.getArea(), request.getNumBed(),
				request.getNumBath(), request.getListType(),
				PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, "last_modified"));
	}

	@Override
	public Listing findById(long id) {
		Optional<Listing> listingOptional = listingRepository.findById(id);
		if (!listingOptional.isPresent()) {
			throw new EntityNotFoundException();
		}
		return listingOptional.get();
	}

	@Override
	public void deleteListing(long id) {
		Optional<Listing> listingOptional = listingRepository.findById(id);
		if (!listingOptional.isPresent()) {
			throw new EntityNotFoundException();
		}
		listingRepository.delete(listingOptional.get());
	}

	@Override
	public Listing update(Long id, ListingRequest request) {
		Listing listing = findById(id);
		User createBy = listing.getUser();
		List<ListingUtilities> listingUtilities = listing.getListingUtilities();
		listingUtilityRepository.deleteAll(listingUtilities);
		listingUtilities = createListingUtilities(request, listing);
		listing = mapper.toPersistent(request);
		listing.setListingUtilities(listingUtilities);
		listing.setId(id);
		listing.setUser(createBy);
		listingRepository.saveAndFlush(listing);
		return listing;
	}

	@Override
	public Listing update(Listing listing) {
		return listingRepository.saveAndFlush(listing);
	}

	private List<ListingUtilities> createListingUtilities(ListingRequest listingRequest, Listing listing) {
		List<Utility> utilities = utilitiesRepository.findUtilityByIdIn(listingRequest.getUtilities());
		List<ListingUtilities> listingUtilities = utilities.stream().map(u -> new ListingUtilities(listing, u))
				.collect(Collectors.toList());
		return listingUtilities;
	}

	public ListingImage saveImage(Long listingId, MultipartFile image) {
		Listing listing = findById(listingId);
		String imageName = listing.getId() + "/" + image.getOriginalFilename();
		String imageUrl = s3Service.uploadFile(imageName, image);
		ListingImage listingImage = new ListingImage();
		listingImage.setUrl(imageUrl);
		listingImage.setListing(listing);
		return listingImageRepository.saveAndFlush(listingImage);
	}

	@Override
	public List<Utility> getUtitlities() {
		return utilitiesRepository.findAll();
	}

	@Override
	public List<Listing> findLatestListing(int limit) {
		return listingRepository.findLatestListing(limit);
	}

	@Autowired
	ListingTypeRepository listingTypeRepository;

	@Override
	public Listing create(ListingRequest listingRequest, Principal principal) {

		if (!listingTypeRepository.findById(listingRequest.getListType()).isPresent()) {
			throw new RequestBindingException("invalid listing type");
		}

		Listing listing = mapper.toPersistent(listingRequest);
		listing.setUser(userService.findUserByEmail(principal.getName()));
		List<ListingUtilities> listingUtilities = listingRequest.getUtilities() != null
				? createListingUtilities(listingRequest, listing)
				: Collections.emptyList();
		listing.setListingUtilities(listingUtilities);
		listing.setStatus(ListingStatus.PENDING);
		listingRepository.saveAndFlush(listing);
		return listing;
	}

}
