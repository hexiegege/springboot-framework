package cn.eartech.framework.configuer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域访问配置
 * @author shanfa
 */
@Component
public class CorsConfigurer implements WebMvcConfigurer {

    /**
     * 添加CORS映射
     *
     * @param registry 跨域注册类
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("*")
                //放行哪些请求方式
                .allowedMethods(HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name())
                //放行哪些原始域(头部信息)
                .allowedHeaders("*")
                // 暴露哪些请求头信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders(HttpHeaders.AUTHORIZATION)
                //是否发送Cookie信息
                .allowCredentials(false)
                .maxAge(3600);
    }

    /**
     * 配置路径匹配
     *
     * @param configurer 配置机
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        //忽略路径访问大小写敏感
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }
}
