package cn.eartech.framework.security;

import cn.eartech.framework.configuer.ApplicationConfigurer;
import cn.eartech.framework.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import static org.apache.commons.codec.CharEncoding.UTF_8;
import static org.springframework.security.oauth2.common.OAuth2AccessToken.BEARER_TYPE;

/**
 * Spring Security的Authentication模块标准认证过程
 * 1.用户使用username和password登录
 * 2.系统验证这个password对于该username是正确的
 * 3.假设第二步验证成功，获取该用户的上下文信息（如他的角色列表）
 * 4.围绕该用户建立安全上下文（security context）
 * 5.用户可能继续进行的一些操作被一个验证控制机制潜在的管理，
 * 这个验证机制会根据当前用户的安全上下文来验证权限。
 * @author shanfa
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private AuthorizationHeaderBlacklistService authorizationHeaderBlacklistService;

    private ApplicationConfigurer applicationConfigurer;

    private UserService userService;

    private ObjectMapper mapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, ApplicationContext applicationContext) {
        super(authenticationManager);
        this.userService = userService;
        this.applicationConfigurer = applicationContext.getBean(ApplicationConfigurer.class);
        this.authorizationHeaderBlacklistService = applicationContext.getBean(AuthorizationHeaderBlacklistServiceImpl.class);
        this.mapper = applicationContext.getBean(ObjectMapper.class);
    }


    /**
     * 过滤器内部处理
     *
     * @param request  HTTP请求
     * @param response HTTP响应
     * @param chain    过滤器链
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取Authorization 请求头
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        // 如果请求头为空或 在黑名单（过期） 则直接返回不进行下边的认证
        if (header == null || !header.startsWith(BEARER_TYPE)
                || authorizationHeaderBlacklistService.isBlacklistContain(header)) {
            //请求转发给过滤器链上下一个对象
            //再继续执行会 SecurityContextHolder.clearContext(); 清除认证对象
            // 此时没有authentication 即没有认证
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            //建立安全上下文   保存认证对象 (一般用于自定义认证成功保存认证对象)
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //他的作用是将请求转发给过滤器链上下一个对象。这里的下一个指的是下一个filter，如果没有filter那就是你请求的资源。
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            response.setCharacterEncoding(UTF_8);
            HashMap<String, String> result = new HashMap<>(1);
            result.put("message", "登录已过期，请重新登录！");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            mapper.writeValue(response.getWriter(), result);
        }

    }

    /**
     * 获取授权
     *
     * @param request HTTP请求
     * @return 基于用户密码的Token授权
     */

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // 验证Authorization 请求头是否正确
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            // 获取请求头对应的user Id
            String useId = Jwts.parser()
                    //applicationConfigurer.getAuthentication().getKey()
                    .setSigningKey("eartech")
                    .parseClaimsJws(token.substring(BEARER_TYPE.length()).trim())
                    .getBody()
                    .getSubject();
            if (useId != null) {
                // 返回授权过的用户授权
                return new UsernamePasswordAuthenticationToken(useId, null, Collections.singleton(new SimpleGrantedAuthority(userService.getById(useId).getAuthority().getValue())));
            }
            return null;
        }
        return null;
    }
}
