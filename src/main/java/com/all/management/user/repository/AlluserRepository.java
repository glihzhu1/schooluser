package com.all.management.user.repository;
import com.all.management.user.model.Alluser;
import io.springlets.data.jpa.repository.DetachableJpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
//@RooJpaRepository(entity = Alluser.class)
public interface AlluserRepository extends DetachableJpaRepository<Alluser, Long> {
//public interface AlluserRepository extends DetachableJpaRepository<Alluser, Long> {

	public Alluser findByLoginId(String loginId);
	
	public List<Alluser> findByLoginIdIgnoreCase(String LoginId);
	public List<Alluser> findByEmailIgnoreCase(String email);
	public List<Alluser> findByLoginIdAndEmailIgnoreCase(String LoginId, String email);
	public List<Alluser> findByLoginIdOrEmailIgnoreCase(String LoginId, String email);
	//public List<Alluser> findByUserActiveAndEmail(boolean userActive, String email);
	
	//this method to support login thru both loginid and email
	@Query("SELECT alluser FROM Alluser alluser"
			+ " WHERE alluser.loginId = :username"
			+ " or UPPER(alluser.email) = UPPER(:username) ")
	public List<Alluser> findUserByLoginOrEmail(@Param("username") String username);

	@Query("SELECT alluser FROM Alluser alluser"
			+ " WHERE alluser.id != :id"
			+ " and ( alluser.loginId = :loginId"
			+ " or alluser.email = :email )")
	public List<Alluser> findByLoginIdOrEmailNotId(@Param("id") Long id, @Param("loginId") String loginId, @Param("email") String email);

	@Query("SELECT alluser FROM Alluser alluser"
			+ " WHERE alluser.id != :id"
			+ " and alluser.loginId = :loginId")
	public List<Alluser> findByLoginIdNotId(@Param("id") Long id, @Param("loginId") String loginId);
	
	@Query("SELECT alluser FROM Alluser alluser"
			+ " WHERE alluser.id != :id"
			+ " and alluser.email = :email ")
	public List<Alluser> findByEmailNotId(@Param("id") Long id, @Param("email") String email);

}
