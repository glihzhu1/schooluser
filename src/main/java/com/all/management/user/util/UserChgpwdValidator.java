package com.all.management.user.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.all.management.user.model.Alluser;

public class UserChgpwdValidator implements Validator {

	public static String ROLE_PREFIX = "ROLE_";
    @Override
    public boolean supports(Class clazz) {
    	return Alluser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	Alluser user = (Alluser) target;

    	if(user.getCfmpwd() != null && user.getNewpwd() != null &&
    			!user.getCfmpwd().equals(user.getNewpwd())) {
    		errors.rejectValue("cfmpwd", "diff_userform_confirmPassword");
    	}

    }

}