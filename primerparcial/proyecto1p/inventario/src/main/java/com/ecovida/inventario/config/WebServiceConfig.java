package com.ecovida.inventario.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWs
public class WebServiceConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ServletRegistrationBean messageDispatcherServlet() {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);  // Ahora estamos inyectando correctamente el contexto
        return new ServletRegistrationBean(servlet, "/soap/*");
    }

    @Bean(name = "productServiceWsdl")
    public Wsdl11Definition defaultWsdl11Definition() {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("ProductServicePort");
        definition.setLocationUri("/soap");
        definition.setTargetNamespace("http://www.example.com/demosoap/gen");

        // Aquí deberías cargar tu esquema XSD para generar el WSDL
        XsdSchema productSchema = new SimpleXsdSchema(new ClassPathResource("product.xsd")); // Asegúrate de que el archivo product.xsd esté en la carpeta src/main/resources
        definition.setSchema(productSchema);

        return definition;
    }
}
