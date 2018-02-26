package com.all.management.user.web;

import com.all.management.user.model.Alluser;
import com.all.management.user.repository.AlluserRepository;
import com.all.management.user.service.api.AlluserService;
import com.all.management.user.util.UserChgpwdValidator;
import com.all.management.user.util.UserCreateEditValidator;

import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;

import java.util.GregorianCalendar;
import java.util.Locale;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;

@Controller
@RequestMapping(value = "/allusers/{alluser}",name = "AllusersItemThymeleafController",produces = MediaType.TEXT_HTML_VALUE)
/**
 * = AllusersItemThymeleafController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = Alluser.class, type = ControllerType.ITEM)
@RooThymeleaf
public class AllusersItemThymeleafController {

	private static final Logger logger = LoggerFactory.getLogger(AllusersItemThymeleafController.class);
	
	@Autowired
	private AlluserRepository alluserRepository;
	
	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private AlluserService alluserService;

	/**
     * TODO Auto-generated method documentation
     * 
     * @return AlluserService
     */
    public AlluserService getAlluserService() {
        return alluserService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluserService
     */
    public void setAlluserService(AlluserService alluserService) {
        this.alluserService = alluserService;
    }

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MessageSource messageSource;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MethodLinkBuilderFactory<AllusersItemThymeleafController> itemLink;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MethodLinkBuilderFactory<AllusersCollectionThymeleafController> collectionLink;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param alluserService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public AllusersItemThymeleafController(AlluserService alluserService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setAlluserService(alluserService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(AllusersItemThymeleafController.class));
        //MethodLinkBuilderFactory<AllusersCollectionThymeleafController> colLink = linkBuilder.of(AllusersCollectionThymeleafController.class);
        //setCollectionLink(colLink);
        setCollectionLink(linkBuilder.of(AllusersCollectionThymeleafController.class));
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return MessageSource
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param messageSource
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<AllusersItemThymeleafController> getItemLink() {
        return itemLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<AllusersItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<AllusersCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<AllusersCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @param locale
     * @param method
     * @return Alluser
     */
    @ModelAttribute
    public Alluser getAlluser(@PathVariable("alluser") Long id, Locale locale, HttpMethod method) {
        Alluser alluser = null;
        if (HttpMethod.PUT.equals(method)) {
            alluser = alluserService.findOneForUpdate(id);
        } else {
            alluser = alluserService.findOne(id);
        }
        
        if (alluser == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] {"Alluser", id}, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return alluser;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Alluser alluser, Model model) {
        model.addAttribute("alluser", alluser);
        return new ModelAndView("allusers/show");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Alluser alluser, Model model) {
        model.addAttribute("alluser", alluser);
        return new ModelAndView("allusers/showInline :: inline-content");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param dataBinder
     */
    @InitBinder("alluser")
    public void initAlluserBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param model
     */
    public void populateFormats(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("lastUpdateDate_date_format", "MM-dd-yyyy");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param model
     */
    public void populateForm(Model model) {
        populateFormats(model);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Alluser alluser, Model model) {
        populateForm(model);
        
        model.addAttribute("alluser", alluser);
        return new ModelAndView("allusers/edit");
    }

    @GetMapping(value = "/chgpwd-form", name = "chgpwdForm")
    public ModelAndView chgpwdForm(@ModelAttribute Alluser alluser, Model model) {
        populateForm(model);
        
        model.addAttribute("alluser", alluser);
        //model.addAttribute("newpwd", new String(""));
        return new ModelAndView("allusers/chgpwd");
    }
    
	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Alluser alluser, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
    	UserCreateEditValidator userValidator = new UserCreateEditValidator(alluserRepository);
        userValidator.validate(alluser, result);
    	
        if (result.hasErrors()) {
            populateForm(model);
            
            return new ModelAndView("allusers/edit");
        }
        // Concurrency control
        Alluser existingAlluser = getAlluserService().findOne(alluser.getId());
        if(alluser.getVersion() != existingAlluser.getVersion() && StringUtils.isEmpty(concurrencyControl)){
            populateForm(model);
            model.addAttribute("alluser", alluser);
            model.addAttribute("concurrency", true);
            return new ModelAndView("allusers/edit");
        } else if(alluser.getVersion() != existingAlluser.getVersion() && "discard".equals(concurrencyControl)){
            populateForm(model);
            model.addAttribute("alluser", existingAlluser);
            model.addAttribute("concurrency", false);
            return new ModelAndView("allusers/edit");
        } else if(alluser.getVersion() != existingAlluser.getVersion() && "apply".equals(concurrencyControl)){
            // Update the version field to be able to override the existing values
            alluser.setVersion(existingAlluser.getVersion());
        }
        /*PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(alluser.getPasswordHash());
        alluser.setPasswordHash(encodedPassword);*/
        alluser.setLastUpdateDate(GregorianCalendar.getInstance());
        Alluser savedAlluser = getAlluserService().save(alluser);
        UriComponents showURI = getItemLink().to(AllusersItemThymeleafLinkFactory.SHOW).with("alluser", savedAlluser.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Alluser alluser) {
        getAlluserService().delete(alluser);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(value = "/chgpwd", name = "chgpwd")
    public ModelAndView chgpwd(@Valid @ModelAttribute Alluser alluser, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
    	UserChgpwdValidator userValidator = new UserChgpwdValidator();
        userValidator.validate(alluser, result);
    	
        if (result.hasErrors()) {
            populateForm(model);
            
            return new ModelAndView("allusers/chgpwd");
        }
        // Concurrency control
        Alluser existingAlluser = getAlluserService().findOne(alluser.getId());
        if(alluser.getVersion() != existingAlluser.getVersion() && StringUtils.isEmpty(concurrencyControl)){
            populateForm(model);
            model.addAttribute("alluser", alluser);
            model.addAttribute("concurrency", true);
            return new ModelAndView("allusers/chgpwd");
        } else if(alluser.getVersion() != existingAlluser.getVersion() && "discard".equals(concurrencyControl)){
            populateForm(model);
            model.addAttribute("alluser", existingAlluser);
            model.addAttribute("concurrency", false);
            return new ModelAndView("allusers/chgpwd");
        } else if(alluser.getVersion() != existingAlluser.getVersion() && "apply".equals(concurrencyControl)){
            // Update the version field to be able to override the existing values
            alluser.setVersion(existingAlluser.getVersion());
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(alluser.getNewpwd());
        alluser.setPasswordHash(encodedPassword);
        alluser.setLastUpdateDate(GregorianCalendar.getInstance());
        Alluser savedAlluser = getAlluserService().save(alluser);
        UriComponents showURI = getItemLink().to(AllusersItemThymeleafLinkFactory.SHOW).with("alluser", savedAlluser.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }
}
