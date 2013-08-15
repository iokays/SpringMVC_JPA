package com.iokays.security.manager;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Service;

@Service("customWebInvocationPrivilegeEvaluator")
public class CustomWebInvocationPrivilegeEvaluator implements WebInvocationPrivilegeEvaluator{

	@Override
	public boolean isAllowed(String uri, Authentication authentication) {
		return isAllowed(null, uri, null, authentication);
	}

	@Override
	public boolean isAllowed(String contextPath, String uri, String method, Authentication authentication) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
