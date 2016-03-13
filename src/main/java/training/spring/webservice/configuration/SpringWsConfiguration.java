package training.spring.webservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

@Configuration
@EnableWs
@ComponentScan("training.spring.webservice")
public class SpringWsConfiguration extends WsConfigurerAdapter {
    @Bean
    public DefaultWsdl11Definition user() {
        DefaultWsdl11Definition user = new DefaultWsdl11Definition();
        user.setPortTypeName("UserService");
        user.setLocationUri("/");
        user.setTargetNamespace("http://localhost:8080/cinema");
        user.setSchema(userSchema());
        return user;
    }

    @Bean
    public XsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("user.xsd"));
    }

    @Bean
    public MarshallingPayloadMethodProcessor marshallingPayloadMethodProcessor() {
        return new MarshallingPayloadMethodProcessor(marshaller());
    }

    @Bean
    public Marshaller marshaller() {
        CastorMarshaller marshaller = new CastorMarshaller();
        marshaller.setMappingLocation(new ClassPathResource("mapping.xml"));
        return marshaller;
    }

    @Override
    public void addArgumentResolvers(List<MethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(marshallingPayloadMethodProcessor());
    }

    @Override
    public void addReturnValueHandlers(List<MethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(marshallingPayloadMethodProcessor());
    }
}
