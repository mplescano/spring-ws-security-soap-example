package com.bernardomg.example.swss.common;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configurable
public class WsdlConfig {

    @Bean
    public DefaultWsdl11Definition entities(@Value("${wsdl.portTypeName}") String wsdlPortTypeName,
                                            @Value("${wsdl.locationUri}") String wsdlLocationUri,
                                            @Value("${wsdl.targetNamespace}") String wsdlTargetNamespace,
                                            XsdSchema schemaEntity) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName(wsdlPortTypeName);
        definition.setLocationUri(wsdlLocationUri);
        definition.setTargetNamespace(wsdlTargetNamespace);
        definition.setSchema(schemaEntity);
        return definition;
    }

    @Bean
    public XsdSchema schemaEntity(@Value("${wsdl.path}") Resource wsdlPath) {
        return new SimpleXsdSchema(wsdlPath);
    }
}
