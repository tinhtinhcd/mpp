package com.housing.app.service.impl;

import java.security.Principal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;

import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.exception.EntityNotFoundException;
import com.housing.app.model.ListingImage;
import com.housing.app.repo.ListingImageRepository;
import com.housing.app.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.housing.app.dto.ListingRequest;
import com.housing.app.mapper.ListingMapper;
import com.housing.app.model.Listing;
import com.housing.app.model.ListingUtilities;
import com.housing.app.model.User;
import com.housing.app.model.Utility;
import com.housing.app.repo.ListingRepository;
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
	public Listing create(ListingRequest listingRequest, Principal principal) {

		Listing listing = mapper.toPersistent(listingRequest);
		listing.setUser(userService.findUserByEmail(principal.getName()));
		listingRepository.saveAndFlush(listing);

		List<ListingUtilities> listingUtilities = createListingUtilities(listingRequest, listing);

		listing.setListingUtilities(listingUtilities);
		listingRepository.saveAndFlush(listing);

		return listing;

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
	public Listing update(Long id, @Valid ListingRequest request) {

		Optional<Listing> listingOptional = listingRepository.findById(id);
		if (!listingOptional.isPresent()) {
			throw new EntityNotFoundException();
		} else {
			Listing listing = listingOptional.get();
			User createBy = listing.getUser();

			List<ListingUtilities> listingUtilities = listing.getListingUtilities();
			listingUtilityRepository.deleteAll(listingUtilities);
			listingUtilities = createListingUtilities(request, listing);
			listing = mapper.toPersistent(request);
			listing.setListingUtilities(listingUtilities);
			listing.setId(id);
			listing.setUser(createBy);
			listing.setUpdatedAt(Calendar.getInstance().getTime());
			listingRepository.saveAndFlush(listing);

			return listing;
		}
	}

	private List<ListingUtilities> createListingUtilities(ListingRequest listingRequest, Listing listing) {
		
		List<ListingUtilities> listingUtilities = Arrays.stream(listingRequest.getUtilities()).boxed()
				.collect(Collectors.toList()).stream()
				.map(u -> new ListingUtilities(listing, utilitiesRepository.getOne(u))).collect(Collectors.toList());

		listingUtilityRepository.saveAll(listingUtilities);
		listingUtilityRepository.flush();
		
		return listingUtilities;
	}

	public ListingImage saveImage(Long listingId, MultipartFile image) {
		Listing listing = findById(listingId);
		String imageName = listing.getId() +"/"+ image.getOriginalFilename();
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
}
