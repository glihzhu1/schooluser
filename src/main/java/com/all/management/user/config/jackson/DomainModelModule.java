package com.all.management.user.config.jackson;
import com.all.management.user.model.Alluser;
import com.all.management.user.web.AlluserJsonMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDomainModelModule;

@JsonComponent
/**
 * = DomainModelModule
 TODO Auto-generated class documentation
 *
 */
@RooDomainModelModule
public class DomainModelModule extends SimpleModule {

	/**
     * TODO Auto-generated constructor documentation
     * 
     */
    public DomainModelModule() {
        // Mixin registration
        
        setMixInAnnotation(Alluser.class, AlluserJsonMixin.class);
    }
}
