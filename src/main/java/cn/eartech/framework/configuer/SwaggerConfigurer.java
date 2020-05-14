package cn.eartech.framework.configuer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 * @author shanfa
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigurer {

    /**
     * 获取具有API信息的摘要
     *
     * @return 具有API信息的摘要Docket类实例
     */
    @Bean
    public Docket getDocket() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        parameterBuilder.name("Authorization").description("user token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .pathMapping("/")
                .select()
                // TODO: 替换包名
                .apis(RequestHandlerSelectors.basePackage("cn.eartech.framework.controller"))
                .build()
                .enableUrlTemplating(true)
                .globalOperationParameters(parameters);
    }


    /**
     * 配置Swagger UI页面中显示的API信息
     *
     * @return 包含API信息的一个ApiInfo类的实例
     */
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .version("1.0.0")
                .title("后台系统Web API")
                .build();
    }
}
