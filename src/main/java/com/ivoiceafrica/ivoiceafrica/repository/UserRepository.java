package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoiceafrica.ivoiceafrica.DTO.ProfileDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findUserByUsername(String username);
	
	//add a method to sort by created date
	public List<User> findAllByOrderByCreatedDateDesc();
	
	Optional<User> findFirstUserByUsername(String username);
	
	List<User> findUsersByUsername(String username);
	
	List<User> findUsersByFirstName(String firstName);
	
	List<User> findUsersByLastName(String lastName);
	
	List<User> findUsersByPhone(String phone);
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE users u set u.user_status_id = :userStatus where u.user_id = :userId", nativeQuery = true)
	public int updateUserStatus(@Param("userStatus")int userStatus, @Param("userId")int userId);
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE users u set u.first_name = :#{#profile.firstName}, u.last_name = :#{#profile.lastName}, u.gender = :#{#profile.sex},"
			+ "u.country = :#{#profile.country}, u.nationality = :#{#profile.nationality}, u.address = :#{#profile.streetAddress},"
			+ " u.phone = :#{#profile.mobileNumber} where u.user_id = :#{#profile.userId} ", nativeQuery = true)
	public int updateUserInfo(@Param("profile")ProfileDTO profileDTo);
	
	Optional<User> findFirstUserByUsernameAndUpassword(String username, String password);
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE users u set u.upassword = :uPassword where u.user_id = :userId", nativeQuery = true)
	public int updatePassword(@Param("uPassword")String uPassword, @Param("userId")int userId);
	
	
	@Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.id = :roleId")
    List<User> findUserByRole(@Param("roleId")int roleId);
	
	@Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.id = :roleId AND user.username = :username")
    List<User> findUserByRoleAndUsername(@Param("roleId")int roleId, @Param("username")String username);
	
	@Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.id = :roleId AND user.firstName = :firstname")
    List<User> findUserByRoleAndFirstname(@Param("roleId")int roleId, @Param("firstname")String firstname);
	
	@Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.id = :roleId AND user.lastName = :lastname")
    List<User> findUserByRoleAndLastname(@Param("roleId")int roleId, @Param("lastname")String lastname);
	
	@Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.id = :roleId AND user.phone = :phone")
    List<User> findUserByRoleAndPhone(@Param("roleId")int roleId, @Param("phone")String phone);

}
