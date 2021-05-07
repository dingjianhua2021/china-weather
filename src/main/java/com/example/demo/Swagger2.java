package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2
 *
 * @author Peter.Ding
 * @date 2021/04/28
 */@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class Swagger2 {
  /**
   * 创建rest api
   *
   * @return {@link Docket}
   */public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  /**
   * api的信息
   *
   * @return {@link ApiInfo}
   */private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        // 页面标题
        .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
        // 创建人信息
        .contact(new Contact("Peter.Ding", "123", "123"))
        // 版本号
        .version("1.0")
        // 描述
        .description("API 描述")
        .build();
  }
}
