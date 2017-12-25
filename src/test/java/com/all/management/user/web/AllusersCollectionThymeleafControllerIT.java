package com.all.management.user.web;
import com.all.management.user.model.dod.AlluserDataOnDemand;
import com.all.management.user.model.dod.AlluserFactory;
import com.all.management.user.service.api.AlluserService;
import io.springlets.boot.test.autoconfigure.web.servlet.SpringletsWebMvcTest;
//import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.test.RooThymeleafControllerIntegrationTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;

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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private Pageable pageable;

    //@Mock
    //private GlobalSearch search;
    
    @Mock
    private Sort sort;

    @Mock
    private Iterator<Sort.Order> iterator;

    private QueryDslRepositorySupportExt<Object> support;
    
    //@Autowired
	//AlluserDataOnDemand dod;
    
    public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	/*public GlobalSearch getSearch() {
		return search;
	}

	public void setSearch(GlobalSearch search) {
		this.search = search;
	}*/

	@Before
    public void setUp() {
		//dod.init();
    	mvc = MockMvcBuilders.standaloneSetup(new AllusersCollectionThymeleafController()).build();
    	//pageable = Mockito.mock(Pageable.class);
    	//search = new GlobalSearch("a", false);
    	//search = Mockito.mock(GlobalSearch.class);
    	
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
    
   /* @Test
    public void testListProducts() throws Exception {
    	assertThat(this.alluserServiceService).isNotNull();
    	
    	MvcResult result= this.mvc.perform(get("/allusers"))
    		.andExpect(status().isOk()).andReturn();
    	
    	MockHttpServletResponse mockResponse=result.getResponse();
        assertThat(mockResponse.getContentType()).isEqualTo("text/html;charset=UTF-8");
    }*/
}
