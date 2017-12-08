package com.all.management.user.repository;
import com.all.management.user.model.Alluser;
import io.springlets.data.jpa.repository.DetachableJpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
//@RooJpaRepository(entity = Alluser.class)
public interface AlluserRepository extends DetachableJpaRepository<Alluser, Long> {
//public interface AlluserRepository extends DetachableJpaRepository<Alluser, Long> {

	public Alluser findByLoginId(String loginId);
}
