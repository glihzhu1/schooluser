package com.all.management.user.model;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.all.management.user.model.dod.AlluserDataOnDemand;
import com.all.management.user.model.dod.AlluserFactory;
import com.all.management.user.repository.AlluserRepository;
import com.all.management.user.service.api.AlluserService;

import io.springlets.data.domain.GlobalSearch;
/**
 * = AlluserTest
 TODO Auto-generated class documentation
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AlluserTest {

	@Autowired
	AlluserDataOnDemand dod;
	
	@Mock
	private AlluserRepository alluserRepository;
	
	@Mock
    private AlluserService alluserService;
	
	@Mock
    private Pageable pageable;
	
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
    
    private static final Long PERSON_COUNT = Long.valueOf(4);
    private static final Long PERSON_ID = Long.valueOf(5);
    private static final String LOGIN_ID = "login_id";
    private static final String EMAIL = "email@test.com";

    @Test
    public void count() {
        when(alluserService.count()).thenReturn(PERSON_COUNT);
        Long personCount = alluserService.count();

        verify(alluserService, times(1)).count();
        verifyNoMoreInteractions(alluserService);

        assertEquals(PERSON_COUNT, personCount);
    }
    
    @Test
    public void testFindOne() {
    	Alluser obj = dod.getRandomAlluser();
        Assert.assertNotNull("Data on demand for 'Alluser' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Alluser' failed to provide an identifier", id);
        
    	when(alluserService.findOne(id)).thenReturn(obj);
    	Alluser returned = alluserService.findOne(id);
        
        verify(alluserService, times(1)).findOne(id);
        verifyNoMoreInteractions(alluserService);
        
        assertEquals(obj, returned);
    }
    
    @Test
    public void testFindByLoginId() {
    	Alluser person = new Alluser();
    	person.setId(PERSON_ID);
    	person.setLoginId(LOGIN_ID);
    	person.setEmail(EMAIL);
    	
        when(alluserRepository.findByLoginId(LOGIN_ID)).thenReturn(person);
        
        Alluser actualPerson = alluserRepository.findByLoginId(LOGIN_ID);
        
        ArgumentCaptor<String> pageArgument = ArgumentCaptor.forClass(String.class);
        verify(alluserRepository, times(1)).findByLoginId(pageArgument.capture());
        verifyNoMoreInteractions(alluserRepository);
        
    }
    
    @Test
    public void testFindAll() {
    	Alluser obj = dod.getRandomAlluser();
        Assert.assertNotNull("Data on demand for 'Alluser' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Alluser' failed to provide an identifier", id);
        
        List<Alluser> expected = new ArrayList<Alluser>();
        expected.add(obj);
        Page expectedPage = new PageImpl(expected);
        
        when(alluserService.findAll(any(GlobalSearch.class), any(Pageable.class))).thenReturn(expectedPage);
        
        Page<Alluser> actualPage = alluserService.findAll(any(GlobalSearch.class), any(Pageable.class));
        
        ArgumentCaptor<Pageable> pageArgument = ArgumentCaptor.forClass(Pageable.class);
        verify(alluserService, times(1)).findAll(any(GlobalSearch.class), pageArgument.capture());
        verifyNoMoreInteractions(alluserService);
        
        Assert.assertNotNull("Data on demand for 'Alluser' failed to initialize correctly", expectedPage);
        
        long numberElem = actualPage.getTotalElements();
        assertEquals(expected.size(), numberElem);
    }
    
    @Test
    public void delete() {
    	Alluser deleted = new Alluser();
        deleted.setId(PERSON_ID);
        deleted.setLoginId("login-id");
        deleted.setEmail("email@test.com");
    	
        //when(alluserService.findOne(PERSON_ID)).thenReturn(deleted);
        alluserService.delete(deleted);
        
        //verify(alluserService, times(1)).findOne(PERSON_ID);
        verify(alluserService, times(1)).delete(deleted);
        verifyNoMoreInteractions(alluserService);
        
    }
    
    @Test
    public void create() {
    	Alluser created = new Alluser();
    	//created.setId(PERSON_ID);
    	created.setLoginId("login-id");
    	created.setEmail("email@test.com");
        
        when(alluserService.save(any(Alluser.class))).thenReturn(created);
        
        Alluser returned = alluserService.save(created);

        ArgumentCaptor<Alluser> personArgument = ArgumentCaptor.forClass(Alluser.class);
        verify(alluserService, times(1)).save(personArgument.capture());
        verifyNoMoreInteractions(alluserService);

        assertPerson(created, personArgument.getValue());
        assertEquals(created, returned);
    }
    
    private void assertPerson(Alluser expected, Alluser actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getLoginId(), actual.getLoginId());
        assertEquals(expected.getEmail(), expected.getEmail());
    }
    
}
