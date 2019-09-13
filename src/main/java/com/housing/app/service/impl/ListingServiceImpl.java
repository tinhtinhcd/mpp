package com.housing.app.service.impl;

import java.security.Principal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.housing.app.dto.ListingRequest;
import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.mapper.ListingMapper;
import com.housing.app.model.Listing;
import com.housing.app.model.ListingUtilities;
import com.housing.app.model.User;
import com.housing.app.postgresql.ResourceNotFoundException;
import com.housing.app.repo.ListingRepository;
import com.housing.app.repo.ListingUtilityRepository;
import com.housing.app.repo.UtilitiesRepository;
import com.housing.app.service.ListingService;
import com.housing.app.service.UserService;

@Service
public class ListingServiceImpl implements ListingService {

	private final ListingMapper mapper = Mappers.getMapper(ListingMapper.class);

	@Autowired
	private final ListingRepository listingRepository;

	@Autowired
	UserService userService;

	@Autowired
	ListingUtilityRepository listingUtilityRepository;

	@Autowired
	UtilitiesRepository utilitiesRepository;

	@Autowired
	public ListingServiceImpl(ListingRepository listingRepository) {
		this.listingRepository = listingRepository;
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
		return listingRepository.searchListing(request.getLatitude(), request.getLongitude(), request.getRadius(),
				request.getPrice(), request.getArea(),
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
			throw new ResourceNotFoundException("Listing not exists!");
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

}
