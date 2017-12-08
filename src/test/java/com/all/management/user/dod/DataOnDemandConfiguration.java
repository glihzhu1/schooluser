package com.all.management.user.dod;
import com.all.management.user.model.dod.AlluserDataOnDemand;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.roo.addon.jpa.annotations.dod.RooJpaDataOnDemandConfiguration;

@TestConfiguration
/**
 * = DataOnDemandConfiguration
 TODO Auto-generated class documentation
 *
 */
@RooJpaDataOnDemandConfiguration
public class DataOnDemandConfiguration {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private EntityManager entityManager;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param entityManager
     */
    @Autowired
    public DataOnDemandConfiguration(EntityManager entityManager) {
        setEntityManager(entityManager);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return AlluserDataOnDemand
     */
    @Bean
    public AlluserDataOnDemand alluserDataOnDemand() {
        return new AlluserDataOnDemand(getEntityManager());
    }
}
