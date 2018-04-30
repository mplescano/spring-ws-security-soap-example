package com.bernardomg.example.swss.password.plain.wss4j;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.bernardomg.example.swss.password.common.wss4j.WSConfig;
import com.bernardomg.example.swss.password.common.wss4j.WSInterceptorConfig;

@SpringBootConfiguration
@ComponentScan(excludeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
@Import(value = { WSConfig.class, WSInterceptorConfig.class })
public class WSPasswordPlainWss4jContext {

}
