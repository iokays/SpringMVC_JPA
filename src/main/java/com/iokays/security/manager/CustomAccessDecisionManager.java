package com.iokays.security.manager;

import java.util.Collection;

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
			Object[] array_ = configAttributes.toArray();
			Object[] grantedAuthorities = authentication.getAuthorities().toArray();
			int _start = 0;
			for (int i = 0; i < array_.length; ++i) {
				int _end = grantedAuthorities.length - 1;
				if (_start >= grantedAuthorities.length) new AccessDeniedException("");
				int _left = Integer.valueOf(((SecurityConfig)array_[i]).getAttribute());
				while (_start <= _end) {
					int _mark = _start + (_end - _start) / 2;
					int _right;
					try {
						_right = Integer.valueOf(((GrantedAuthority)grantedAuthorities[_mark]).getAuthority());
					} catch (Exception e) {
						break;
					}
					if (_left == _right) return;
					if (_left > _right) _start = _mark + 1;
					if (_left < _right) _end = _mark - 1;
				}
			}
			
			
			
//			Iterator<ConfigAttribute> _iterator = configAttributes.iterator();
//			while(_iterator.hasNext()) {
//				ConfigAttribute configAttribute_ = _iterator.next();
//				String needRole_ = ((SecurityConfig)configAttribute_).getAttribute();
//				
//				for (GrantedAuthority grantedAuthority_ : authentication.getAuthorities()) {
//					if (needRole_.trim().equals(grantedAuthority_.getAuthority().trim())) {
//						return;
//					}
//				}
//			}
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
