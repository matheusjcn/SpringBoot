package com.appsdeveloper.app.ws;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.appsdeveloper.app.ws.io.entity.AuthorityEntity;
import com.appsdeveloper.app.ws.io.entity.RoleEntity;
import com.appsdeveloper.app.ws.io.entity.UserEntity;
import com.appsdeveloper.app.ws.io.repositories.AuthoritoryRepository;
import com.appsdeveloper.app.ws.io.repositories.RoleRepository;
import com.appsdeveloper.app.ws.io.repositories.UserRepository;
import com.appsdeveloper.app.ws.shared.Utils;

@Component
public class InitialsUsersSetup {

	@Autowired
	AuthoritoryRepository authoritoryRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	Utils utils;
	
	@Autowired
	UserRepository userRepository;

	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("Run Application....");

		AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
		AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
		AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");
		
		RoleEntity roleUser =  createRole("ROLE_USER", Arrays.asList(readAuthority, writeAuthority));
		
		RoleEntity roleAdmin = createRole("ROLE_ADMIN", Arrays.asList(readAuthority, writeAuthority, deleteAuthority));
		
		if(roleAdmin == null) return;
		
		UserEntity adminUser = new UserEntity();
		adminUser.setFirstName("Admin");
		adminUser.setLastName("Adorno");
		adminUser.setEmail("admin@test.com");
		adminUser.setEmailVerificationStatus(true);
		adminUser.setUserId(utils.generateUserId(30));
		adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("123456"));
		adminUser.setRoles(Arrays.asList(roleAdmin));
		
		userRepository.save(adminUser);
		
		UserEntity commomUser = new UserEntity();
		commomUser.setFirstName("User");
		commomUser.setLastName("Normal");
		commomUser.setEmail("user@test.com");
		commomUser.setEmailVerificationStatus(true);
		commomUser.setUserId(utils.generateUserId(30));
		commomUser.setEncryptedPassword(bCryptPasswordEncoder.encode("123456"));
		commomUser.setRoles(Arrays.asList(roleUser));
		
		userRepository.save(commomUser);
	}

	@Transactional
	public AuthorityEntity createAuthority(String name) {

		AuthorityEntity authority = authoritoryRepository.findByName(name);
		if (authority == null) {
			authority = new AuthorityEntity(name);
			authoritoryRepository.save(authority);
		}
		return authority;
	}

	@Transactional
	public RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {

		RoleEntity role = roleRepository.findByName(name);
		if (role == null) {
			role = new RoleEntity(name);
			role.setAuthorities(authorities);
			roleRepository.save(role);
			System.out.println("[CreateRole] - save");
		}
		return role;
	}

}
