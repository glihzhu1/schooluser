package com.all.management.user.service.api;
import com.all.management.user.model.Alluser;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.roo.addon.layers.service.annotations.RooService;

/**
 * = AlluserService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = Alluser.class)
public interface AlluserService extends EntityResolver<Alluser, Long> {

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Alluser
     */
    public abstract Alluser findOne(Long id);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     */
    public abstract void delete(Alluser alluser);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    public abstract List<Alluser> save(Iterable<Alluser> entities);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Alluser
     */
    public abstract Alluser save(Alluser entity);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Alluser
     */
    public abstract Alluser findOneForUpdate(Long id);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return List
     */
    public abstract List<Alluser> findAll(Iterable<Long> ids);


	/**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    public abstract List<Alluser> findAll();


	/**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public abstract long count();


	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Alluser> findAll(GlobalSearch globalSearch, Pageable pageable);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Alluser> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
    
    //public abstract List<Alluser> findByLoginIdOrEmailNotId(Long id, String LoginId, String email);

}
