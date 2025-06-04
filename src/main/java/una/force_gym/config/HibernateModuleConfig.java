package main.java.una.force_gym.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilderCustomizer;

@Configuration
public class HibernateModuleConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> {
            Hibernate5Module hibernate5Module = new Hibernate5Module();
            // Ignorar las proxies no inicializadas para evitar excepciones
            hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
            // También es común deshabilitar FORCE_LAZY_LOADING si no quieres que se cargue toda la info automáticamente
            hibernate5Module.disable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
            
            builder.modules(hibernate5Module);
        };
    }
}
