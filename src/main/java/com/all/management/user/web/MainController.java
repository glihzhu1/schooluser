package com.all.management.user.web;
import io.springlets.web.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleafMainController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
/**
 * = MainController
 TODO Auto-generated class documentation
 *
 */
@RooThymeleafMainController
public class MainController {

	/**
     * TODO Auto-generated method documentation
     * 
     * @param model
     * @return String
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        return "index";
    }

    
    @GetMapping("/index")
    public String backIndex(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        return "index";
    }
    
	/**
     * TODO Auto-generated method documentation
     * 
     * @param model
     * @return String
     */
    @GetMapping("/accessibility")
    public String accessibility(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        return "accessibility";
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param template
     * @return String
     */
    @RequestMapping(value = "/js/{template}.js", method = RequestMethod.GET)
    public String javascriptTemplates(@PathVariable("template") String template) {
        if (StringUtils.hasLength(template)) {
            return template.concat(".js");
        }
        throw new NotFoundException("File not found");
    }
    
    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        return "login";
    }
    
    @GetMapping("/error")
    public String error(Model model) {
    	model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        return "error";
    }
}
