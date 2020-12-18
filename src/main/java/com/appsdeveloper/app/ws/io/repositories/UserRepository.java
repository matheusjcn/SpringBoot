package com.appsdeveloper.app.ws.io.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appsdeveloper.app.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);   
	UserEntity findByEmailVerificationToken(String token);
	/*
	@Query(value="SELECT * FROM users u WHERE u.email_verification_status = 'true'",
			countQuery="SELECT count(*) FROM Users u WHERE u.email_verification_status = 'true'",
			nativeQuery=true)
	Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageableResquest);
	
	@Query(value="SELECT * from users u WHERE u.firstName = ?1", 
			nativeQuery = true)
	List<UserEntity> findUserByFirstName(String firstName);  
	
	@Query(value="SELECT * from users u WHERE u.lastName = :lastName", 
			nativeQuery = true)
	List<UserEntity> findUserByLastName(@Param("lastName") String lastName);  
	
	@Query(value="SELECT u.firstName, u.lastName FROM Users u WHERE firstName LIKE %:keyword% or last_name LIKE  %:keyword%", 
			nativeQuery = true)
	List<Object[]> findUserFirstNameAndLastNameByKeyword(@Param("lastName") String keyword);  
	
	@Transactional
	@Modifying
	@Query(value="UPDATE users u set u.EMAIL_VERIFICATION_STATUS=:emailVerificationStatus WHERE u.user_id=:userId")
	void updateUserEmailVerificationStatus(@Param("emailVerificationStatus") boolean emailVerificationStatus, @Param("userId") String userId);
	
	
	@Query("SELECT user FROM UserEntity user WHERE user.userId =:userId")
	UserEntity findUserEntityByUserId(@Param("userId") String userId);
	  
//	@Query("SELECT user.firstName, user.LastName FROM UserEntity userEnti ty user WHERE user.userId =:userId")
//	List<Object[]> getUserEntityFullNameById(@Param("userId") String userId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE UserEntity u set u.EMAIL_VERIFICATION_STATUS=:emailVerificationStatus WHERE u.user_id=:userId")
	void updateUserEntityEmailVerificationStatus (@Param("emailVerificationStatus") boolean emailVerificationStatus, @Param("userId") String userId);
	*/
	
}


 