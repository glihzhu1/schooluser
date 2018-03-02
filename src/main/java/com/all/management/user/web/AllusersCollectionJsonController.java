package com.all.management.user.web;
import com.all.management.user.model.Alluser;
import com.all.management.user.repository.AlluserRepository;
import com.all.management.user.service.api.AlluserService;
import io.springlets.data.domain.GlobalSearch;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
@RequestMapping(value = "/allusers",name = "AllusersCollectionJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = AllusersCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = Alluser.class, type = ControllerType.COLLECTION)
@RooJSON
public class AllusersCollectionJsonController {
	//@Autowired
	//private PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(AllusersCollectionJsonController.class);
	
	private AlluserRepository alluserRepository;
	
	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private AlluserService alluserService;

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
     * TODO Auto-generated constructor documentation
     * 
     * @param alluserService
     */
    @Autowired
    public AllusersCollectionJsonController(AlluserService alluserService, AlluserRepository alluserRepository) {
        this.alluserService = alluserService;
        this.alluserRepository = alluserRepository;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<Alluser>> list(GlobalSearch globalSearch, Pageable pageable) {
        
        Page<Alluser> allusers = getAlluserService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(allusers);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(AllusersCollectionJsonController.class).list(null, null))
            .build().encode();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody Alluser alluser, BindingResult result) {
        
        if (alluser.getId() != null || alluser.getVersion() != null) {        
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(alluser.getPasswordHash());
        alluser.setPasswordHash(encodedPassword);
        Alluser newAlluser = getAlluserService().save(alluser);
        UriComponents showURI = AllusersItemJsonController.showURI(newAlluser);
        
        return ResponseEntity.created(showURI.toUri()).build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param allusers
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<Alluser> allusers, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        getAlluserService().save(allusers);
        
        return ResponseEntity.created(listURI().toUri()).build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param allusers
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<Alluser> allusers, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        getAlluserService().save(allusers);
        
        return ResponseEntity.ok().build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        
        getAlluserService().delete(ids);
        
        return ResponseEntity.ok().build();
    }
    
   //Below is called by the school app
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        try {
            Alluser alluser = Alluser.fromJsonToAlluser(json);
            
            Alluser alluserupdate= alluserRepository.findOne(alluser.getId());
            if(alluserupdate != null) {
            	List<Alluser> result = 
            			alluserRepository.findByLoginIdOrEmailNotId(alluser.getId(), alluser.getLoginId(), alluser.getEmail());
            	//make sure the logid and email is not used by other users
            	if (result == null || result.isEmpty()) {
                	alluserupdate.setLoginId(alluser.getLoginId());
    	            alluserupdate.setEmail(alluser.getEmail());
    	            alluserupdate.setLastUpdateDate(GregorianCalendar.getInstance());
    	            if (alluserRepository.save(alluserupdate) == null) {
    	                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
    	            }
                }
                else if (result.size() == 1){
                	// The loginId or email has been used
                	return new ResponseEntity<String>(result.get(0).toJson(), headers, HttpStatus.FOUND);
                }
                else {
                	// too many duplicated users
                	return new ResponseEntity<String>(headers, HttpStatus.MULTI_STATUS);
                }
            	
	            
            }
            else
            	return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            return new ResponseEntity<String>(headers, HttpStatus.OK);
        } catch (Exception e) {
        	logger.error("An exception error occured: ", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/pwd/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updatePwdFromJson(@RequestBody String json, @PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        try {
            Alluser alluser = Alluser.fromJsonToAlluser(json);
            
            Alluser alluserupdate= getAlluserService().findOne(alluser.getId());
            if(alluserupdate != null) {
            	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(alluser.getPasswordHash());
                alluserupdate.setPasswordHash(encodedPassword);
            	//alluserupdate.setPasswordHash(alluser.getPasswordHash());
	            alluserupdate.setLastUpdateDate(GregorianCalendar.getInstance());
	            if (getAlluserService().save(alluserupdate) == null) {
	                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
	            }
            }
            else
            	return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            return new ResponseEntity<String>(headers, HttpStatus.OK);
        } catch (Exception e) {
        	logger.error("An exception error occured: ", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/emailloginid/{email}/{loginId}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> retrieveUserByEmailAndLoginIdJson(@PathVariable("email") String email, @PathVariable("loginId") String loginId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
        	List<Alluser> result = alluserRepository.findByLoginIdAndEmailIgnoreCase(loginId, email);
            if (result == null || result.isEmpty()) {
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
            else {
            	Alluser alluser = result.get(0);
            	return new ResponseEntity<String>(alluser.toJson(), headers, HttpStatus.OK);
            }
        } catch (Exception e) {
        	logger.error("An exception error occured: ", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> retrieveUserByEmailJson(@PathVariable("email") String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
        	//List<Alluser> result = alluserRepository.findByLoginIdIgnoreCase(loginId);
        	List<Alluser> result = alluserRepository.findByEmailIgnoreCase(email);
            if (result == null || result.isEmpty()) {
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
            else {
            	Alluser alluser = result.get(0);
            	return new ResponseEntity<String>(alluser.toJson(), headers, HttpStatus.OK);
            }
        } catch (Exception e) {
        	logger.error("An exception error occured: ", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/loginId/{loginId}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> retrieveUserByloginIdJson(@PathVariable("loginId") String loginId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
        	//List<Alluser> result = alluserRepository.findByLoginIdIgnoreCase(loginId);
        	List<Alluser> result = alluserRepository.findUserByLoginOrEmail(loginId);
            if (result == null || result.isEmpty()) {
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
            else {
            	Alluser alluser = result.get(0);
            	return new ResponseEntity<String>(alluser.toJson(), headers, HttpStatus.OK);
            }
        } catch (Exception e) {
        	logger.error("An exception error occured: ", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/json/create", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createAndFetchUserJson(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
        	Alluser alluser = Alluser.fromJsonToAlluser(json);
            
        	List<Alluser> result = 
        			alluserRepository.findByLoginIdOrEmailIgnoreCase(alluser.getLoginId(), alluser.getEmail());
            if (result == null || result.isEmpty()) {
            	alluser.setLastUpdateDate(GregorianCalendar.getInstance());
            	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(alluser.getPasswordHash());
                alluser.setPasswordHash(encodedPassword);
            	alluser = alluserRepository.save(alluser);
            	return new ResponseEntity<String>(alluser.toJson(), headers, HttpStatus.CREATED);
            }
            else if (result.size() == 1){
            	// The loginId or email has been used
            	return new ResponseEntity<String>(result.get(0).toJson(), headers, HttpStatus.FOUND);
            }
            else {
            	// too many duplicated users
            	return new ResponseEntity<String>(headers, HttpStatus.MULTI_STATUS);
            }
        } catch (Exception e) {
        	logger.error("An exception error occured: ", e);
        	
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/extId/{extId}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> retrieveUserByIdJson(@PathVariable("extId") String extId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try {
        	Alluser result = alluserService.findOne(Long.valueOf(extId));
            if (result == null) {
            	return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
            else {
            	return new ResponseEntity<String>(result.toJson(), headers, HttpStatus.OK);
            }
        } catch (Exception e) {
        	logger.error("An exception error occured: ", e);
            return new ResponseEntity<String>("{\"ERROR\":"+e.getMessage()+"\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	public AlluserRepository getAlluserRepository() {
		return alluserRepository;
	}

	public void setAlluserRepository(AlluserRepository alluserRepository) {
		this.alluserRepository = alluserRepository;
	}
    
    
}
