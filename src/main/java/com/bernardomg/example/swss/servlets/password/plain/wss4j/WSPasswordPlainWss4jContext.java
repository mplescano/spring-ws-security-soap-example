package com.bernardomg.example.swss.servlets.password.plain.wss4j;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.bernardomg.example.swss.servlets.password.common.wss4j.WSInterceptorConfig;
import com.bernardomg.example.swss.servlets.password.common.wss4j.WSWss4jConfig;

@SpringBootConfiguration
@ComponentScan(excludeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
@Import(value = { WSWss4jConfig.class, WSInterceptorConfig.class })
public class WSPasswordPlainWss4jContext {

}
