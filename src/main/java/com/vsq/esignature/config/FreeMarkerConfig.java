package com.vsq.esignature.config;
//import com.nullbeans.nullbeansserver.freemarker.FreemarkerTemplateWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


/**
 * author: MohammadReza Ahmadi  15.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@Configuration
public class FreeMarkerConfig {

    private final static Logger logger = LoggerFactory.getLogger(FreeMarkerConfig.class);

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates"); //defines the classpath location of the freemarker templates
        freeMarkerConfigurer.setDefaultEncoding("UTF-8"); // Default encoding of the template files
        return freeMarkerConfigurer;
    }

/*    @Bean
    public FreemarkerTemplateWriter freemarkerTemplateWriter(){
        return new FreemarkerTemplateWriter();
    }*/

}