package cn.eartech.framework.configuer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shanfa
 * @Desc 数据源配置类，但log4jdbc作为数据驱动可能无法连接
 * @date 2020/3/24
 * @Version 1.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix="spring.datasource")
public class DataSourceConfigurer {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String platform;
    private String type;
}
