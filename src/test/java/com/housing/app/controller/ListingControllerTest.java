package com.housing.app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Page;

import com.housing.app.dto.ListingRequest;
import com.housing.app.dto.ListingSearchRequest;
import com.housing.app.model.Listing;
import com.housing.app.model.ListingImage;
import com.housing.app.model.ListingType;
import com.housing.app.model.User;
import com.housing.app.service.ListingService;
import com.housing.app.service.ListingTypeService;
import com.housing.app.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class ListingControllerTest {

	@Mock
	private ListingService listingService;

	@Mock
	private UserService userService;

	@Mock
	private ListingTypeService listingTypeService;

	@InjectMocks
	ListingController listingController = new ListingController();

	@Test
	public void getAllMyListing() {
		assertNotNull(listingController.getAllMyListing(mockCurrentUser()));
	}

	@Test
	public void create() {

		ListingRequest request = mock(ListingRequest.class);
		ResponseEntity<Listing> listingResponseEntity = listingController.create(request, mockCurrentUser(),
				mock(BindingResult.class));

		assertNotNull(listingResponseEntity);
		assertEquals(HttpStatus.OK, listingResponseEntity.getStatusCode());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void search() {
		ListingSearchRequest request = mock(ListingSearchRequest.class);
		when(listingService.search(request)).thenReturn(Mockito.mock(Page.class));
		assertNotNull(listingController.search(request));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void similarListing() {
		Listing listing = mock(Listing.class);
		when(listingService.findById(listing.getId())).thenReturn(listing);
		when(listingService.search(ArgumentMatchers.<ListingSearchRequest>any())).thenReturn(Mockito.mock(Page.class));
		assertEquals(HttpStatus.OK, listingController.similarListing(listing.getId()).getStatusCode());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void nearbyListing() {
		Listing listing = mock(Listing.class);
		when(listingService.findById(listing.getId())).thenReturn(listing);
		when(listingService.search(ArgumentMatchers.<ListingSearchRequest>any())).thenReturn(Mockito.mock(Page.class));
		assertEquals(HttpStatus.OK, listingController.nearbyListing(listing.getId()).getStatusCode());
	}

	@Test
	public void viewDetail() {
		assertEquals(HttpStatus.OK, listingController.viewDetail(ArgumentMatchers.anyLong()).getStatusCode());
	}

	@Test
	public void update() {
		Listing listing = mock(Listing.class);
		ListingRequest request = mock(ListingRequest.class);
		assertEquals(HttpStatus.OK, listingController.update(request, listing.getId()).getStatusCode());
	}

	@Test
	public void getAllUtitlites() {
		assertNotNull(listingController.getAllUtitlites());
	}

	@Test
	public void uploadImage() {
		Listing listing = mock(Listing.class);
		MockMultipartFile images = new MockMultipartFile("data", "test.img", "image/jpeg", "".getBytes());

		assertEquals(HttpStatus.OK, listingController.uploadImage(images, listing.getId()).getStatusCode());
	}

	@Test
	public void deleteImage() {
		Listing listing = mock(Listing.class);
		when(listingService.findById(ArgumentMatchers.anyLong())).thenReturn(listing);
		when(listing.getListingImages()).thenReturn(new ArrayList<ListingImage>());
		assertEquals(HttpStatus.OK,
				listingController.deleteImage(listing.getId(), ArgumentMatchers.anyLong()).getStatusCode());
	}

	@Test
	public void getAllListingType() {
		when(listingTypeService.findAll()).thenReturn(new ArrayList<ListingType>());
		assertNotNull(listingController.getAllListingType());
	}

	@PostMapping()
	public void updateListingStatus() {
	}

	private Authentication mockCurrentUser() {
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
		when(userService.findUserByEmail(authentication.getName())).thenReturn(mock(User.class));
		return authentication;
	}

}
