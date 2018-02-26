package com.all.management.user.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.all.management.user.model.Alluser;
import com.all.management.user.repository.AlluserRepository;

public class UserCreateEditValidator implements Validator {

	public static String ROLE_PREFIX = "ROLE_";
	
	private AlluserRepository alluserRepository;
	
	@Autowired
    public UserCreateEditValidator(AlluserRepository alluserRepository) {
        this.alluserRepository = alluserRepository;
    }
	
    @Override
    public boolean supports(Class clazz) {
    	return Alluser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	Alluser user = (Alluser) target;

    	if(user.getAppRole() != null && user.getAppRole().trim().length() >0) {
    		String[] allroles = user.getAppRole().split(",");
    		for (String role : allroles) {
    			if(!role.startsWith(ROLE_PREFIX)) {
    	    		errors.rejectValue("appRole", "user_role_prefix");
    			}
    		}
    		
    	}
    	
    	if (user.getId() != null ) {
    		//For update
    		List<Alluser> result = alluserRepository.findByLoginIdNotId(user.getId(), user.getLoginId());
    		if (result.size() >= 1) {
        		errors.rejectValue("loginId", "user_loginId_used");
            }
        	
        	result = alluserRepository.findByEmailNotId(user.getId(), user.getEmail());
    		if (result.size() >= 1) {
        		errors.rejectValue("email", "user_email_used");
            }
        	
    	}
    	else {
    		//For create
    		Alluser alluser = alluserRepository.findByLoginId(user.getLoginId());
    		if (alluser != null) {
        		errors.rejectValue("loginId", "user_loginId_used");
            }
        	
    		List<Alluser> result = alluserRepository.findByEmailIgnoreCase(user.getEmail());
    		if (result.size() >= 1) {
        		errors.rejectValue("email", "user_email_used");
            }
    	}

    }

}