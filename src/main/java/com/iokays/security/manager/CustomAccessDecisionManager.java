package com.iokays.security.manager;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service("customAccessDecisionManager")
public class CustomAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (null != configAttributes) {
			Iterator<ConfigAttribute> _iterator = configAttributes.iterator();
			
			while(_iterator.hasNext()) {
				ConfigAttribute configAttribute_ = _iterator.next();
				String needRole_ = ((SecurityConfig)configAttribute_).getAttribute();
				
				for (GrantedAuthority grantedAuthority_ : authentication.getAuthorities()) {
					if (needRole_.trim().equals(grantedAuthority_.getAuthority().trim())) {
						return;
					}
				}
				
			}
		}
		
		throw new AccessDeniedException("");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
}
