package com.taokaixiang.ssm.demo.v1.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author Kaixiang Tao
 * @date 2019/12/5
 */
public class WebServiceAuthenticationToken extends AbstractAuthenticationToken {

    private String username;

    private String password;

    public WebServiceAuthenticationToken(String username, String password, List<GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.password = password;
        setAuthenticated(true);
    }

    @Override public Object getCredentials() {
        return password;
    }

    @Override public Object getPrincipal() {
        return username;
    }
}
