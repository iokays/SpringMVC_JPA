package com.iokays.security.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.iokays.authority.repository.AuthorityRepository;
import com.iokays.resource.repository.ResourceRepository;

@Service("customSecurityMetadataSource")
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	@PostConstruct
	public void loadResourceDefine() {
		List<String> _authorityIds = authorityRepository.getIds();
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		for (String _authorityId : _authorityIds) {
			ConfigAttribute _configAttribute = new SecurityConfig(_authorityId);
			List<String> _resourceValues =  resourceRepository.getValues(_authorityId);
			for (String _resourceValue : _resourceValues) {
				if (resourceMap.containsKey(_resourceValue)) {
					Collection<ConfigAttribute> _value = resourceMap.get(_resourceValue);
					_value.add(_configAttribute);
					resourceMap.put(_resourceValue, _value);
				} else {
					Collection<ConfigAttribute> _values = new ArrayList<ConfigAttribute>();
					_values.add(_configAttribute);
					resourceMap.put(_resourceValue, _values);
				}
			}
		}
		
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String _url = ((FilterInvocation) object).getRequestUrl();
		int firstQuestionMarkIndex = _url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
            _url = _url.substring(0, firstQuestionMarkIndex);
        }
		
		Iterator<String> _iterator = resourceMap.keySet().iterator();
		
		while (_iterator.hasNext()) {
			String url_ = _iterator.next();
			if (_url.equals(url_)) {
				return resourceMap.get(url_);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	@Resource
	private AuthorityRepository authorityRepository;
	@Resource
	private ResourceRepository resourceRepository;
	
}
