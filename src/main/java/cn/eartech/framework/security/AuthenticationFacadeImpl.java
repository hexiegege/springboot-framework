package cn.eartech.framework.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 身份认证门面实现类
 * @author shanfa
 */
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public String getUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public List<GrantedAuthority> getUserGrantedAuthority() {
        return new ArrayList<>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }
}