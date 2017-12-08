package com.all.management.user.web;
import com.all.management.user.model.dod.AlluserFactory;
import com.all.management.user.service.api.AlluserService;
import io.springlets.boot.test.autoconfigure.web.servlet.SpringletsWebMvcTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.test.RooThymeleafControllerIntegrationTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringletsWebMvcTest(controllers = AllusersCollectionThymeleafController.class,secure = false)
/**
 * = AllusersCollectionThymeleafControllerIT
 TODO Auto-generated class documentation
 *
 */
@RooThymeleafControllerIntegrationTest(targetClass = AllusersCollectionThymeleafController.class)
public class AllusersCollectionThymeleafControllerIT {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    @Autowired
    private MockMvc mvc;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    @MockBean
    private AlluserService alluserServiceService;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private AlluserFactory factory = new AlluserFactory();

	/**
     * TODO Auto-generated method documentation
     * 
     * @return MockMvc
     */
    public MockMvc getMvc() {
        return mvc;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return AlluserService
     */
    public AlluserService getAlluserServiceService() {
        return alluserServiceService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluserServiceService
     */
    public void setAlluserServiceService(AlluserService alluserServiceService) {
        this.alluserServiceService = alluserServiceService;
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
     * Test method example. To be implemented by developer.
     * 
     */
    @Test
    public void testMethodExample() {
        // Setup
        // Previous tasks
        
        // Exercise
        // Execute method to test
        
        // Verify
        // Check results with assertions
    }
}
