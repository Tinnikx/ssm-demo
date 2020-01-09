package com.taokaixiang.ssm.demo.v1.auth.provider;

import com.taokaixiang.ssm.demo.v1.auth.StandardAuthenticationToken;
import com.taokaixiang.ssm.demo.v1.auth.WebServiceAuthenticationToken;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaixiang Tao
 * @date 2019/11/20
 */
@Lazy
@Component
public class StandardAuthenticationProvider implements AuthenticationProvider {


    @Override public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("123"));
        return new WebServiceAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), authorities);
    }

    @Override public boolean supports(Class<?> authentication) {
        return authentication.equals(StandardAuthenticationToken.class);
    }
}
