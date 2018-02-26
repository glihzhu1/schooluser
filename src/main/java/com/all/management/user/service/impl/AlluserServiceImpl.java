package com.all.management.user.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all.management.user.model.Alluser;
import com.all.management.user.repository.AlluserRepository;
import com.all.management.user.repository.AlluserRepositoryCustom;
import com.all.management.user.service.api.AlluserService;

import io.springlets.data.domain.GlobalSearch;

@Service
@Transactional(readOnly = true)
/**
 * = AlluserServiceImpl
 TODO Auto-generated class documentation
 *
 */
public class AlluserServiceImpl implements AlluserService {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private AlluserRepository alluserRepository;
    
    private AlluserRepositoryCustom alluserRepositoryCustom;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param alluserRepository
     */
    @Autowired
    public AlluserServiceImpl(AlluserRepository alluserRepository, AlluserRepositoryCustom alluserRepositoryCustom) {
        setAlluserRepository(alluserRepository);
        setAlluserRepositoryCustom(alluserRepositoryCustom);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return AlluserRepository
     */
    public AlluserRepository getAlluserRepository() {
        return alluserRepository;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluserRepository
     */
    public void setAlluserRepository(AlluserRepository alluserRepository) {
        this.alluserRepository = alluserRepository;
    }

	public AlluserRepositoryCustom getAlluserRepositoryCustom() {
		return alluserRepositoryCustom;
	}

	public void setAlluserRepositoryCustom(AlluserRepositoryCustom alluserRepositoryCustom) {
		this.alluserRepositoryCustom = alluserRepositoryCustom;
	}

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void delete(Alluser alluser) {
        getAlluserRepository().delete(alluser);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    @Transactional
    public List<Alluser> save(Iterable<Alluser> entities) {
        return getAlluserRepository().save(entities);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Alluser> toDelete = getAlluserRepository().findAll(ids);
        getAlluserRepository().deleteInBatch(toDelete);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Alluser
     */
    @Transactional
    public Alluser save(Alluser entity) {
        return getAlluserRepository().save(entity);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Alluser
     */
    public Alluser findOne(Long id) {
        return getAlluserRepository().findOne(id);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Alluser
     */
    public Alluser findOneForUpdate(Long id) {
        return getAlluserRepository().findOneDetached(id);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return List
     */
    public List<Alluser> findAll(Iterable<Long> ids) {
        return getAlluserRepository().findAll(ids);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    public List<Alluser> findAll() {
        return getAlluserRepository().findAll();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public long count() {
        return getAlluserRepository().count();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Alluser> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getAlluserRepositoryCustom().findAll(globalSearch, pageable);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Alluser> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getAlluserRepositoryCustom().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /*public List<Alluser> findByLoginIdOrEmailNotId(Long id, String LoginId, String email) {
    	return getAlluserRepositoryCustom().findByLoginIdOrEmailNotId(id, LoginId, email);
    }*/
    
	/**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    public Class<Alluser> getEntityType() {
        return Alluser.class;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }
}
