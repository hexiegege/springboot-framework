package cn.eartech.framework.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static org.apache.commons.codec.CharEncoding.UTF_8;

/**
 * 角色权限访问拒绝处理
 * 一些框架通过验证用户的session处理无效的csrf token，这会导致一些问题。
 * 通过配置AccessDeniedHandler使用不同方式处理不合法的CsrfTokenException，
 * 我们可以替换spring security crsf protection
 * 默认的http 403 access denied处理方式。
 * @author shanfa
 */
@Component
public class CommonAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding(UTF_8);
        HashMap<String, String> result = new HashMap<>(1);
        result.put("message", "权限不足");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        mapper.writeValue(response.getWriter(), result);
    }
}
