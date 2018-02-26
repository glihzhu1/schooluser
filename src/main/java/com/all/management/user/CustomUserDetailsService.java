package com.all.management.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.all.management.user.model.Alluser;
import com.all.management.user.repository.AlluserRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	private final AlluserRepository userRepository;
	//private final UserRolesRepository userRolesRepository;
	
	@Autowired
    public CustomUserDetailsService(AlluserRepository userRepository) {
        this.userRepository = userRepository;
        //this.userRolesRepository=userRolesRepository;
    }
	
        
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Alluser user=userRepository.findByLoginId(username);
		List<Alluser> users = userRepository.findUserByLoginOrEmail(username);
		
		if(null == users || users.size() < 1){
			throw new UsernameNotFoundException("No user present with username: "+username);
		}else{
			Alluser user= users.get(0);
			//List<String> userRoles= new ArrayList<String>();
			//userRoles.add(user.getAppRole());
			return new CustomUserDetails(user, user.getAppRole());
		}
	}
		
}
