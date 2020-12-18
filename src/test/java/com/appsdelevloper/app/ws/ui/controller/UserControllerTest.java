package com.appsdelevloper.app.ws.ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.appsdeveloper.app.ws.service.impl.UserServiceImpl;
import com.appsdeveloper.app.ws.shared.dto.AddressDTO;
import com.appsdeveloper.app.ws.shared.dto.UserDto;
import com.appsdeveloper.app.ws.ui.controller.UserController;
import com.appsdeveloper.app.ws.ui.model.response.UserRest;

class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	UserServiceImpl userService;
	
	UserDto userDto;

	final String USER_ID = "bfshdjbjfbsjb";
	
	
	@BeforeEach 
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userDto = new UserDto();
		userDto.setFirstName("Tester");
		userDto.setLastName("Tester");
		userDto.setEmail("Tester");
		userDto.setEmailVerificationStatus(Boolean.FALSE);
		userDto.setEmailVerificationToken(null);
		userDto.setUserId(USER_ID);
		userDto.setAddresses(getAddressesDto());
		userDto.setEncryptedPassword("qweasdzxc");
	}

	@Test
	final void testGetUser() {
		when(userService.getUserByUserId(Mockito.anyString())).thenReturn(userDto);
		
		UserRest userRest = userController.getUser(USER_ID);
		assertNotNull(userRest);
		assertEquals(USER_ID, userRest.getUserId());
		assertEquals(userDto.getFirstName(), userRest.getLastName());
		assertTrue(userDto.getAddresses().size() == userRest.getAddresses().size());
	}

	private List<AddressDTO> getAddressesDto() {
		AddressDTO addressDto = new AddressDTO();
		addressDto.setType("shipping");
		addressDto.setCity("VG");
		addressDto.setCountry("Brasil");
		addressDto.setPostalCode("98754321");
		addressDto.setStreetName("Name Street name");

		AddressDTO billingAddressDto = new AddressDTO();
		addressDto.setType("billing");
		addressDto.setCity("Cuiabá");
		addressDto.setCountry("Brasil");
		addressDto.setPostalCode("98754321");
		addressDto.setStreetName("Name Street name");

		List<AddressDTO> addresses = new ArrayList<>();
		addresses.add(addressDto);
		addresses.add(billingAddressDto);

		return addresses;
	}
	
}
