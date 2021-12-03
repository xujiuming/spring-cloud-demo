package com.ming.user.config;

import com.ming.user.StartUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置
 *
 * @author ming
 * @date 2021-12-03 16:31:50
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
                        .description("用户服务api")
                        .title("用户服务api")
                        .version("1.0.0")
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage(StartUser.class.getPackageName()))
                .paths(PathSelectors.any())
                .build()
                ;
    }
}
