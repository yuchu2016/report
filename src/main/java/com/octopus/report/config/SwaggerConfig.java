package com.octopus.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luqinglin
 * Date: 2017-12-28
 * Time: 16:07
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * swagger配置
     * @return
     */
    @Bean
    public Docket createRestApi() {

        /**
         * swagger需要扫描的包
         */
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.octopus.report.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 界面信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Postgresql 查询Api")
                .description("postgresql 查询Api")
                .termsOfServiceUrl("http://192.168.22.27:8080/aoi/result")
                .contact("hahaha")
                .version("1.0")
                .build();
    }


}
