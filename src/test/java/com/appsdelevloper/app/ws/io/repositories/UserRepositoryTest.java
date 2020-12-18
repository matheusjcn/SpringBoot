package com.appsdelevloper.app.ws.io.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.appsdeveloper.app.ws.io.entity.AddressEntity;
import com.appsdeveloper.app.ws.io.entity.UserEntity;
import com.appsdeveloper.app.ws.io.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	static boolean recordsCreate = false;

	@BeforeEach
	void setUp() throws Exception {
		if(!recordsCreate) {
			createRecords();
			recordsCreate = true;
		}
	}
/*
	@Test
	@Disabled
	//@Order(1)
	void testGetVerifiedUsers() {
		Pageable pageableResquest = PageRequest.of(0,2);
		Page<UserEntity> pages = userRepository.findAllUsersWithConfirmedEmailAddress(pageableResquest);
		assertNotNull(pages);
		
		/*List<UserEntity> userEntities = pages.getContent();
		assertNotNull(userEntities);
		assertTrue(userEntities.size() == 1);*
	}


	@Test
	@Disabled
	//@Order(2)
	void testFindUserByFirstName() {
		String firstName = "Test";
		
		List<UserEntity> users = userRepository.findUserByFirstName(firstName);
		System.out.println(users.toString());
		assertNotNull(users);
		assertTrue(users.size() == 2);
		
		UserEntity user = users.get(0);
		assertTrue(user.getFirstName().equals(firstName ));
	}
	
	
	@Test
	@Disabled
	//@Order(3)
	void testFindUserByLastName() {
		String lastName = "lastTest";
		
		List<UserEntity> users = userRepository.findUserByLastName(lastName);
		assertNotNull(users);
		assertTrue(users.size() == 1);
		
		UserEntity user = users.get(0);
		assertTrue(user.getFirstName().equals(lastName ));
	}
	
	
	@Test
	@Order(4)
	void testFindByKeyword() {
		String keyword = "tes";
		
		List<Object[]> users = userRepository.findUserFirstNameAndLastNameByKeyword(keyword);
		assertNotNull(users);
		
		
		Object[] user = users.get(0);
		String userFirstName = String.valueOf(user[0]);
		String userLastName = String.valueOf(user[1]);
		
		assertNotNull(userFirstName);
		assertNotNull(userLastName);
		
		System.out.println("First name = " + userFirstName);
		System.out.println("First name = " + userLastName);
	}
	
	@Test
	@Order(5)
	final void updateUserEmailVerificationStatus() {

		boolean newEmailVerificationStatus = true;
		userRepository.updateUserEmailVerificationStatus(newEmailVerificationStatus, "123abc");
		UserEntity storedUserDetails = userRepository.findByUserId("123abc");
		boolean storedEmailVerificationStatus = storedUserDetails.getEmailVerificationStatus();
		assertTrue(storedEmailVerificationStatus == newEmailVerificationStatus);

	}
	
	
	@Test
	@Order(6)
	final void testfindUserEntityByUserId() {
		String userId = "123abc";
		UserEntity userEntity =  userRepository.findUserEntityByUserId(userId); 
		
		assertNotNull(userEntity);
		assertTrue(userEntity.getUserId().equals(userId)); 
	}
	
	
	@Test
	@Order(7)
	final void testGetUserEntityFullNameById() {
		String userId = "123abc";
		//List<Object[]>  records =  userRepository.getUserEntityFullNameById(userId);
		
		List<Object[]>  records = null;
		assertTrue(records.size() == 1);
		
		Object[] userDetails = records.get(0);
		 
		String firstName = String.valueOf(userDetails[0]);
		String lastName = String.valueOf(userDetails[1]);
		
		assertNotNull(firstName);
		assertNotNull(lastName);
	}
	
	
	@Test
	@Order(8)
	final void testUpdateUserEntityEmailVerificationStatus() {

		boolean newEmailVerificationStatus = true;
		userRepository.updateUserEntityEmailVerificationStatus(newEmailVerificationStatus, "123abc");
		UserEntity storedUserDetails = userRepository.findByUserId("123abc");
		boolean storedEmailVerificationStatus = storedUserDetails.getEmailVerificationStatus();
		assertTrue(storedEmailVerificationStatus == newEmailVerificationStatus);

	}
	
	*/
	private void createRecords() {
		
		System.out.println("[Create - Record]");
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("Test");
		userEntity.setLastName("lastTest");
		userEntity.setUserId("123abc");
		userEntity.setEmail("emailcxcdf@email.com");
		userEntity.setEncryptedPassword("xaxbxc");
		userEntity.setEmailVerificationStatus(true);
		
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setType("shipping");
		addressEntity.setAddressId("1azxza21");
		addressEntity.setCity("Cuiaba");
		addressEntity.setCountry("Brasil");
		addressEntity.setPostalCode("12312321");
		addressEntity.setStreetName("Baker Street");
		
		AddressEntity addressEntity2 = new AddressEntity();
		addressEntity2.setType("shipping");
		addressEntity2.setAddressId("1abx21");
		addressEntity2.setCity("Cuiaba2");
		addressEntity2.setCountry("Brasil");
		addressEntity2.setPostalCode("12312321");
		addressEntity2.setStreetName("Baker Street");

		List<AddressEntity> addresses = new ArrayList<>();
		addresses.add(addressEntity);
		//addresses.add(addressEntity2);
		userEntity.setAddresses(addresses);

		userRepository.save(userEntity);

		System.out.println(" --- --- --- --- --- ");
		System.out.println("[Create - Record2]");
		
		UserEntity userEntity2 = new UserEntity();
		userEntity2.setFirstName("Test");
		userEntity2.setLastName("OtherlastTest");
		userEntity2.setUserId("123abAfsdxas");
		userEntity2.setEmail("email2aas@email.com");
		userEntity2.setEncryptedPassword("3fdf3d2agss");
		userEntity2.setEmailVerificationStatus(true);
		
		AddressEntity addressEntity3 = new AddressEntity();
		addressEntity3.setType("shipping");
		addressEntity3.setAddressId("1ad2zxa1");
		addressEntity3.setCity("Cuiaba");
		addressEntity3.setCountry("Brasil");
		addressEntity3.setPostalCode("12312321");
		addressEntity3.setStreetName("Baker Street");
		
		AddressEntity addressEntity4 = new AddressEntity();
		addressEntity4.setType("shipping");
		addressEntity4.setAddressId("1asdsdz21");
		addressEntity4.setCity("Cuiaba2");
		addressEntity4.setCountry("Brasil");
		addressEntity4.setPostalCode("12312321");
		addressEntity4.setStreetName("Baker Street");

		List<AddressEntity> addresses2 = new ArrayList<>();
		addresses.add(addressEntity3);
		//addresses.add(addressEntity4);
		userEntity2.setAddresses(addresses2);
		userRepository.save(userEntity2);
		
		System.out.println("_-_");
		
		
		recordsCreate = true;
	}


}
