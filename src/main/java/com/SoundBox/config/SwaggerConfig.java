package com.SoundBox.config;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static io.swagger.models.auth.In.HEADER;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.List;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api() {
        return new Docket(SWAGGER_2).select()
                .apis(withClassAnnotation(RestController.class))
                .paths(PathSelectors.any()).build()
                .securitySchemes(singletonList(new ApiKey("Token Access", AUTHORIZATION, HEADER.name())))
                .securityContexts(singletonList(securityContext())).apiInfo(apiInfo());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(mountPredicates(PathSelectors.ant(SecurityConfig.PREFIX + "/**")))
                .build();
    }

    @SuppressWarnings("Guava")
    private Predicate<String> mountPredicates(Predicate<String> mainPath) {
        List<Predicate<String>> predicates = of(SecurityConfig.NO_AUTH_PERMITED_PATHS).map(PathSelectors::ant).collect(toList());
        Predicate<String> notInList = not(or(predicates));
        return and(mainPath, notInList);
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope scope = new AuthorizationScope("ADMIN", "accessEverything");
        AuthorizationScope[] scopes = new AuthorizationScope[]{scope};
        return singletonList(new SecurityReference("Token Access", scopes));
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "SoundBox - API", 
          "Documentation from API.", 
          "", 
          "", 
          new Contact("Luciana A Costa", "www.soundbox.com", "ti@soundbox.com"), 
          "License of API", "API license URL", Collections.emptyList());
    }
}
