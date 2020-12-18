package com.appsdelevloper.app.ws.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.appsdeveloper.app.ws.shared.Utils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UtilsTest {

	@Autowired
	Utils utils;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGenerateUserId() {

		String userId = utils.generateUserId(30);
		String userId2 = utils.generateUserId(30);

		assertNotNull(userId);
		assertNotNull(userId2);

		assertTrue(userId.length() == 30);
		assertTrue(!userId.equalsIgnoreCase(userId2));
	}

	@Test
	void testHasTokenNotExpired() {

		String token = utils.generateEmailVerificationToken("5dbas4wghdsd1a32wd");
		assertNotNull(token);

		boolean hasTokenExpired = Utils.hasTokenExpired(token);

		assertFalse(hasTokenExpired);
	}
	
	@Test
	final void testHasTokenExpired() {
		String expiredToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsYXJpQHRlc3Rlci5jb20iLCJleHAiOjE2MDc2MDE0MTd9.34kocxXgAuVzMZSpRtE46ZV_vdHBpuhRO9QJUHyed03_k-kBYb5-fqBeLDeg-yfeoRPjOQSkAKzf-22ag3me6Q";
		boolean hasTokenExpired = Utils.hasTokenExpired(expiredToken);
		assertTrue(hasTokenExpired);
	}

}
