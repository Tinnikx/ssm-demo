package com.taokaixiang.ssm.demo.v1.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author Kaixiang Tao
 * @date 2019/12/5
 */
public class StandardAuthenticationToken extends AbstractAuthenticationToken {

    private String username;

    private String password;

    public StandardAuthenticationToken (String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
    }

    @Override public Object getCredentials() {
        return password;
    }

    @Override public Object getPrincipal() {
        return username;
    }
}
