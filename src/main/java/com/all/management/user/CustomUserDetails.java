package com.all.management.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.all.management.user.model.Alluser;

public class CustomUserDetails extends com.all.management.user.model.Alluser implements UserDetails {	
	
	private static final long serialVersionUID = 1L;
	//private List<String> userRoles;
	private String userRoles;

	public CustomUserDetails(Alluser user, String userRoles){
		super(user);
		this.userRoles=userRoles;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//String roles=StringUtils.collectionToCommaDelimitedString(userRoles);			
		return AuthorityUtils.commaSeparatedStringToAuthorityList(userRoles);
	}

	/*public CustomUserDetails(Alluser user,List<String> userRoles){
		super(user);
		this.userRoles=userRoles;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String roles=StringUtils.collectionToCommaDelimitedString(userRoles);			
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}*/
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}


	@Override
	public String getUsername() {
		return super.getLoginId();
	}
	
	@Override
	public String getPassword() {
		return super.getPasswordHash();
	}
}
