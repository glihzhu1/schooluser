package com.all.management.user.repository;

import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import org.springframework.transaction.annotation.Transactional;
import com.all.management.user.model.Alluser;
import com.all.management.user.model.QAlluser;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;

@Transactional(readOnly = true)
/**
 * = AlluserRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */ 
@RooJpaRepositoryCustomImpl(repository = AlluserRepositoryCustom.class)
public class AlluserRepositoryImpl extends QueryDslRepositorySupportExt<Alluser> implements AlluserRepositoryCustom {

    /**
     * TODO Auto-generated constructor documentation
     */
    AlluserRepositoryImpl() {
        super(Alluser.class);
    }

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String LOGIN_ID = "loginId";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String PASSWORD_HASH = "passwordHash";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String EMAIL = "email";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String APP_ROLE = "appRole";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String USER_ACTIVE = "userActive";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String LAST_UPDATE_DATE = "lastUpdateDate";

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Alluser> findAll(GlobalSearch globalSearch, Pageable pageable) {
        
        QAlluser alluser = QAlluser.alluser;
        
        JPQLQuery<Alluser> query = from(alluser);
        
        Path<?>[] paths = new Path<?>[] {alluser.loginId,alluser.passwordHash,alluser.email,alluser.appRole,alluser.userActive,alluser.lastUpdateDate};        
        applyGlobalSearch(globalSearch, query, paths);
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(LOGIN_ID, alluser.loginId)
			.map(PASSWORD_HASH, alluser.passwordHash)
			.map(EMAIL, alluser.email)
			.map(APP_ROLE, alluser.appRole)
			.map(USER_ACTIVE, alluser.userActive)
			.map(LAST_UPDATE_DATE, alluser.lastUpdateDate);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, alluser);
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
        
        QAlluser alluser = QAlluser.alluser;
        
        JPQLQuery<Alluser> query = from(alluser);
        
        Path<?>[] paths = new Path<?>[] {alluser.loginId,alluser.passwordHash,alluser.email,alluser.appRole,alluser.userActive,alluser.lastUpdateDate};        
        applyGlobalSearch(globalSearch, query, paths);
        
        // Also, filter by the provided ids
        query.where(alluser.id.in(ids));
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(LOGIN_ID, alluser.loginId)
			.map(PASSWORD_HASH, alluser.passwordHash)
			.map(EMAIL, alluser.email)
			.map(APP_ROLE, alluser.appRole)
			.map(USER_ACTIVE, alluser.userActive)
			.map(LAST_UPDATE_DATE, alluser.lastUpdateDate);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, alluser);
    }
}