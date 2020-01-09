package com.taokaixiang.ssm.demo.v1.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kaixiang Tao
 * @date 2019/12/2
 */
public class StandardAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    protected StandardAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("123"));
        //        authentication.setAuthenticated(false);

        // test Authentication failure
//        throw new PreAuthenticatedCredentialsNotFoundException("test authentication failure");

//        return new UsernamePasswordAuthenticationToken("kaixiang",
//            "123", authorities);
        // 正确的授权方式是使用 AuthenticationManager 进行授权认证
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        StandardAuthenticationToken authRequest = new StandardAuthenticationToken(username, password);
        setDetails(request, authRequest);
        return getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request, StandardAuthenticationToken authRequest) {
        // 此处可以在 security 配置文件中配置自己的 AuthenticationDetailsSource
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
