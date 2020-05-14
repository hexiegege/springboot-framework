package cn.eartech.framework.configuer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

/**
 * web设置  枚举转换 网络请求参数转换
 * @author shanfa
 */

@Configuration
public class HttpParamConfigurer {

    private final RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    public HttpParamConfigurer(RequestMappingHandlerAdapter handlerAdapter) {
        this.handlerAdapter = handlerAdapter;
    }

    /**
     * 添加枚举值value到各种枚举的转换
     */
    @PostConstruct
    public void addStringToSexEnumConverter() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
        genericConversionService.addConverter(new StringToSexEnumConverter());
        genericConversionService.addConverter(new StringToAuthorityEnumConverter());

    }

}
