package com.appsdelevloper.app.ws.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appsdeveloper.app.ws.exception.UserServiceException;
import com.appsdeveloper.app.ws.io.entity.AddressEntity;
import com.appsdeveloper.app.ws.io.entity.UserEntity;
import com.appsdeveloper.app.ws.io.repositories.PasswordResetTokenRepository;
import com.appsdeveloper.app.ws.io.repositories.UserRepository;
import com.appsdeveloper.app.ws.service.impl.UserServiceImpl;
import com.appsdeveloper.app.ws.shared.MailJetSES;
import com.appsdeveloper.app.ws.shared.Utils;
import com.appsdeveloper.app.ws.shared.dto.AddressDTO;
import com.appsdeveloper.app.ws.shared.dto.UserDto;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	Utils utils;

	@Mock
	MailJetSES mailJetSES;

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Mock
	PasswordResetTokenRepository passwordResetTokenRepository;

	UserEntity userEntity;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		// Stub
		userEntity = new UserEntity();

		userEntity.setId(1L);
		userEntity.setFirstName("FirstName");
		userEntity.setLastName("LastName");
		userEntity.setUserId("07SiPARUsrBa57v2lSPUiJJ4sO7CJF");
		userEntity.setEncryptedPassword("$2a$10$Mab17D.KJHLBQqiSURZMxex2IXfVUctxKI7cPkq2vNMHvluiau.wy");
		userEntity.setEmail("test@email.com");
		userEntity.setEmailVerificationToken("testtesttesttest");

		userEntity.setAddresses(getAddressesEntity());
	}

	@Test
	final void testGetUser() {

		// Mocking user
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(userEntity);

		UserDto userDto = userService.getUser("test@email.com");

		assertNotNull(userDto);
		assertEquals("FirstName", userDto.getFirstName());
	}

	@Test
	final void testGetUser_UsernameNotFoundException() {
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class, () -> {
			userService.getUser("test@test.com");
		});
	}

	@Test
	final void testCreateUser_CreateUserServiceException() {
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(userEntity);

		UserDto userDto = new UserDto();
		userDto.setAddresses(getAddressDto());
		userDto.setFirstName("Testador");
		userDto.setLastName("El testador");
		userDto.setPassword("123456");
		userDto.setEmail("test@email.com");
		
		assertThrows(UserServiceException.class, () -> {
			userService.createUser(userDto);
		});
	}

	@Test
	final void testCreateUser() {

		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);
		when(utils.generateAddressId(Mockito.anyInt())).thenReturn("07SiPARUsrBa57v2lSPUiJJ4sO7CJF");
		when(utils.generateUserId(Mockito.anyInt())).thenReturn("07SiPARUsrBa57v2lSPUiJJ4sO7CJF");
		when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("07SiPARUsrBa57v2lSPUiJJ4sO7CJF");
		when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
		Mockito.doNothing().when(mailJetSES).verifyEmail(Mockito.any(UserDto.class));

		UserDto userDto = new UserDto();
		userDto.setAddresses(getAddressDto());
		userDto.setFirstName("Testador");
		userDto.setLastName("El testador");
		userDto.setPassword("123456");
		userDto.setEmail("test@email.com");

		UserDto storedUserDetails = userService.createUser(userDto);
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
		assertNotNull(storedUserDetails.getUserId());

		assertEquals(storedUserDetails.getAddresses().size(), userEntity.getAddresses().size());

		verify(utils, times(2)).generateAddressId(30);
		verify(bCryptPasswordEncoder, times(1)).encode("123456");
		verify(userRepository, times(1)).save(Mockito.any(UserEntity.class));
	}

	private List<AddressDTO> getAddressDto() {
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

	private List<AddressEntity> getAddressesEntity() {
		List<AddressDTO> addresses = getAddressDto();

		Type listType = new TypeToken<List<AddressEntity>>() {
		}.getType();

		return new ModelMapper().map(addresses, listType);
	}

}
