package com.alex.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 19:40
 **/
@Configuration
public class SessionConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/editArticle");

    }
}
