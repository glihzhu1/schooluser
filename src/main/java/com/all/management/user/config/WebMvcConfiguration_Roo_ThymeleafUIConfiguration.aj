// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.all.management.user.config;

import com.all.management.user.config.WebMvcConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

privileged aspect WebMvcConfiguration_Roo_ThymeleafUIConfiguration {
    
    declare parents: WebMvcConfiguration implements ApplicationContextAware;
    
    /**
     * TODO Auto-generated attribute documentation
     * 
     */
    @Autowired
    private ThymeleafProperties WebMvcConfiguration.thymeleafProperties;
    
    /**
     * TODO Auto-generated attribute documentation
     * 
     */
    @Autowired
    private TemplateEngine WebMvcConfiguration.templateEngine;
    
    /**
     * TODO Auto-generated attribute documentation
     * 
     */
    private ApplicationContext WebMvcConfiguration.applicationContext;
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return ThymeleafProperties
     */
    public ThymeleafProperties WebMvcConfiguration.getThymeleafProperties() {
        return thymeleafProperties;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return TemplateEngine
     */
    public TemplateEngine WebMvcConfiguration.getTemplateEngine() {
        return templateEngine;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return ApplicationContext
     */
    public ApplicationContext WebMvcConfiguration.getApplicationContext() {
        return applicationContext;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param applicationContext
     */
    public void WebMvcConfiguration.setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return ThymeleafViewResolver
     */
    @Bean
    public ThymeleafViewResolver WebMvcConfiguration.javascriptThymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(getTemplateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("application/javascript");
        resolver.setViewNames(new String[] {"*.js"});
        resolver.setCache(getThymeleafProperties().isCache());
        return resolver;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return SpringResourceTemplateResolver
     */
    @Bean
    public SpringResourceTemplateResolver WebMvcConfiguration.javascriptTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(getApplicationContext());
        resolver.setPrefix("classpath:/templates/fragments/js/");
        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCheckExistence(true);
        resolver.setCacheable(getThymeleafProperties().isCache());
        return resolver;
    }
    
}
