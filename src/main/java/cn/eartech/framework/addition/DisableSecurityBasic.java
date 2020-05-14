package cn.eartech.framework.addition;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * 关闭SpringBoot Security 的Basic 密码验证
 * 需要加上@Configuration
 * 还需要在启动类加上@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
 * @author shanfa
 */
//@Configuration
public class DisableSecurityBasic extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll();
    }

}
