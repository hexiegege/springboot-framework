package cn.eartech.framework.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * 身份认证接口
 * @author shanfa
        */
public interface AuthenticationFacade {

    /**
     * 获取当前登录用户Id
     *
     * @return 当前登录用户Id
     */
    String getUserId();

    /**
     * 获取当前登录用户授权
     *
     * @return 当前登录用户授权
     */
    List<GrantedAuthority> getUserGrantedAuthority();
}
