package com.mouvie.auth.config.appcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class LazyBeanConfig {

    @Bean
    public AppContext appContext(){
        return new AppContext();
    }
}
