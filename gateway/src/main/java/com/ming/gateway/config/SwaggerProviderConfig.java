package com.ming.gateway.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;

@Component
@Primary
public class SwaggerProviderConfig implements SwaggerResourcesProvider {
    private static final String SWAGGER_URI = "/v2/api-docs";
    @Autowired
    private RouteLocator routeLocator;
    @Autowired
    private GatewayProperties gatewayProperties;

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resourceList = Lists.newArrayList();
        List<String> routeIdList = Lists.newArrayList();
        routeLocator.getRoutes().subscribe(route -> routeIdList.add(route.getId()));
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routeIdList.contains(routeDefinition.getId())).forEach(route -> {
            route.getPredicates().stream()
                    .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                    .forEach(predicateDefinition -> resourceList.add(build(route.getId(),
                            predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                    .replace("/**", SWAGGER_URI))));
        });
        return resourceList;
    }

    private SwaggerResource build(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        return swaggerResource;
    }
}
