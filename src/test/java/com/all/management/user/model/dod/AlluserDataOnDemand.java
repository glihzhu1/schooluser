package com.all.management.user.model.dod;
import com.all.management.user.model.Alluser;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jpa.annotations.dod.RooJpaDataOnDemand;

@Configurable
/**
 * = AlluserDataOnDemand
 TODO Auto-generated class documentation
 *
 */
@RooJpaDataOnDemand(entity = Alluser.class)
public class AlluserDataOnDemand {

	/**
     * Random generator for the entities index.
     * 
     */
    private Random rnd = new SecureRandom();

	/**
     * List of created entities.
     * 
     */
    private List<Alluser> data;

	/**
     * EntityManager to persist the entities.
     * 
     */
    private EntityManager entityManager;

	/**
     * Number of elements to create and persist.
     * 
     */
    private int size;

	/**
     * Factory to create entity instances.
     * 
     */
    private AlluserFactory factory = new AlluserFactory();

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param entityManager
     */
    public AlluserDataOnDemand(EntityManager entityManager) {
        this(entityManager, 10);
    }

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param entityManager
     * @param size
     */
    public AlluserDataOnDemand(EntityManager entityManager, int size) {
        setEntityManager(entityManager);
        setSize(size);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Random
     */
    public Random getRnd() {
        return rnd;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param rnd
     */
    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    public List<Alluser> getData() {
        return data;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param data
     */
    public void setData(List<Alluser> data) {
        this.data = data;
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
     * @return Integer
     */
    public int getSize() {
        return size;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return AlluserFactory
     */
    public AlluserFactory getFactory() {
        return factory;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param factory
     */
    public void setFactory(AlluserFactory factory) {
        this.factory = factory;
    }

	/**
     * Creates a new transient Alluser in a random index out of the initial list of the created entities,
     * with an index greater than {@link AlluserDataOnDemand#getSize()} - 1.
     * 
     * @return Alluser the generated transient {@link Alluser}
     */
    public Alluser getNewRandomTransientAlluser() {
        int randomIndex = getSize() + getRnd().nextInt(Integer.MAX_VALUE - getSize());
        return getFactory().create(randomIndex);
    }

	/**
     * Returns a generated and persisted {@link Alluser} in a given index.
     * 
     * @param index the position of the {@link Alluser} to return
     * @return Alluser the specific {@link Alluser}
     */
    public Alluser getSpecificAlluser(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (getData().size() - 1)) {
            index = getData().size() - 1;
        }
        return getData().get(index);
    }

	/**
     * Returns a generated and persisted {@link Alluser} in a random index.
     * 
     * @return Alluser a random {@link Alluser}
     */
    public Alluser getRandomAlluser() {
        init();
        return getData().get(getRnd().nextInt(getData().size()));
    }

	/**
     * Creates the initial list of generated entities.
     * 
     */
    public void init() {
        int from = 0;
        int to = 10;
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Alluser> cq = cb.createQuery(Alluser.class);
        Root<Alluser> rootEntry = cq.from(Alluser.class);
        CriteriaQuery<Alluser> all = cq.select(rootEntry);
        TypedQuery<Alluser> allQuery = 
            getEntityManager().createQuery(all).setFirstResult(from).setMaxResults(to);
        setData(allQuery.getResultList());
        if (getData() == null) {
            throw new IllegalStateException(
                "Find entries implementation for 'Alluser' illegally returned null");
        }
        if (!getData().isEmpty()) {
            return;
        }
        
        setData(new ArrayList<Alluser>());
        for (int i = from; i < to; i++) {
            Alluser obj = getFactory().create(i);
            try {
                getEntityManager().persist(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter
                      .hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".")
                    .append(cv.getPropertyPath()).append(": ").append(cv.getMessage())
                    .append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            getEntityManager().flush();
            getData().add(obj);
        }
    }
}
