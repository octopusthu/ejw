package com.octopusthu.ejw.security.authorization.dflt;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.octopusthu.ejw.security.authorization.dflt.domain.AuthorizedRequest;
import com.octopusthu.ejw.security.authorization.interfaces.AuthorizationRegistry;
import com.octopusthu.ejw.security.util.SecurityUtils;

public class DefaultAuthorizationRegistry implements AuthorizationRegistry {
    private Map<String, SimpleGrantedAuthority> allAuthorities = new LinkedHashMap<String, SimpleGrantedAuthority>();
    private LinkedHashMap<RequestMatcher, String> requestMap = new LinkedHashMap<RequestMatcher, String>();
    private LinkedHashMap<SimpleGrantedAuthority, String> authorityUrlMap = new LinkedHashMap<SimpleGrantedAuthority, String>();

    @Override
    public Map<String, SimpleGrantedAuthority> getAllAuthorities() {
        return Collections.unmodifiableMap(this.allAuthorities);
    }

    @Override
    public Map<? extends RequestMatcher, String> getRequestMap() {
        return Collections.unmodifiableMap(this.requestMap);
    }

    @Override
    public Map<SimpleGrantedAuthority, String> getAuthorityUrlMap() {
        return Collections.unmodifiableMap(this.authorityUrlMap);
    }

    public void authorities(Map<String, SimpleGrantedAuthority> authorities) {
        this.allAuthorities.putAll(authorities);
    }

    public void authorizedRequests(LinkedHashSet<AuthorizedRequest> authorizedRequests) {
        authorizedRequests.forEach(authorizedRequest -> {
            this.requestMap.put(authorizedRequest.getMatcher(),
                    SecurityUtils.configAttributesToAccessExpression(authorizedRequest.getAuthorities()));
        });
    }

    public void authorityUrlMappings(LinkedHashMap<SimpleGrantedAuthority, String> mappings) {
        if (MapUtils.isNotEmpty(mappings)) {
            this.authorityUrlMap.putAll(mappings);
        }
    }

}
