package com.all.management.user.model;
import com.all.management.user.model.dod.AlluserFactory;
import org.springframework.roo.addon.jpa.annotations.test.RooJpaUnitTest;

/**
 * = AlluserTest
 TODO Auto-generated class documentation
 *
 */
@RooJpaUnitTest(targetClass = Alluser.class)
public class AlluserTest {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private AlluserFactory alluserFactory = new AlluserFactory();

	/**
     * TODO Auto-generated method documentation
     * 
     * @return AlluserFactory
     */
    public AlluserFactory getAlluserFactory() {
        return alluserFactory;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluserFactory
     */
    public void setAlluserFactory(AlluserFactory alluserFactory) {
        this.alluserFactory = alluserFactory;
    }
}
