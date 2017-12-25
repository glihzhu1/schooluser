package com.all.management.user.web;
import com.all.management.user.model.dod.AlluserDataOnDemand;
import com.all.management.user.model.dod.AlluserFactory;
import com.all.management.user.service.api.AlluserService;
import com.querydsl.jpa.JPQLQuery;

import io.springlets.boot.test.autoconfigure.web.servlet.SpringletsWebMvcTest;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.querydsl.core.types.Path;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.roo.addon.web.mvc.controller.annotations.test.RooJsonControllerIntegrationTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringletsWebMvcTest(controllers = AllusersCollectionJsonController.class,secure = false)
/**
 * = AllusersCollectionJsonControllerIT
 TODO Auto-generated class documentation
 *
 */
@RooJsonControllerIntegrationTest(targetClass = AllusersCollectionJsonController.class)
public class AllusersCollectionJsonControllerIT {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    //@Autowired
    //private MockMvc mvc;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    //@MockBean
    private AlluserService alluserService;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private AlluserFactory factory = new AlluserFactory();

    //private QueryDslRepositorySupportExt<Object> support;
    
	/**
     * TODO Auto-generated method documentation
     * 
     * @return MockMvc
     */
    /*public MockMvc getMvc() {
        return mvc;
    }*/

	/**
     * TODO Auto-generated method documentation
     * 
     * @return AlluserService
     */
    public AlluserService getAlluserService() {
        return alluserService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluserService
     */
    public void setAlluserService(AlluserService alluserService) {
        this.alluserService = alluserService;
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
    /*
    @MockBean
	AlluserDataOnDemand dod;*/
    
    /*@Before
    public void setUp() throws Exception {
      support = new QueryDslRepositorySupportExt<Object>(Object.class) {
        @Override
        protected JPQLQuery<Object> applyPagination(Pageable pageable, JPQLQuery<Object> query) {
          return query;
        }
      };
      
      dod.init();
    }*/
    
	/**
     * Test method example. To be implemented by developer.
     * 
     */
    //@Test
    public void testMethodExample() {
        // Setup
        // Previous tasks
        
        // Exercise
        // Execute method to test
        
        // Verify
        // Check results with assertions
    }
    
    /*@Test
    public void testListUsers() throws Exception {
    	assertThat(this.alluserService).isNotNull();
    	
    	globalSearch = new GlobalSearch("ad");
    	//MvcResult result= this.mvc.perform(get("/allusers"))
    	MvcResult result= this.mvc.perform(get("/allusers"))
    		.andExpect(status().isOk()).andReturn();
    		//.andExpect(MockMvcResultMatchers.view().name("allusers/list")).andReturn();
    		//.andExpect(MockMvcResultMatchers.model().attributeExists("contents")).andReturn();
    	
    	MockHttpServletResponse mockResponse=result.getResponse();
        assertThat(mockResponse.getContentType()).isEqualTo("application/json;charset=UTF-8");
    }*/
}
