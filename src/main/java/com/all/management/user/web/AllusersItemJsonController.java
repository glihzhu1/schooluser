package com.all.management.user.web;
import com.all.management.user.model.Alluser;
import com.all.management.user.service.api.AlluserService;
import io.springlets.web.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
@RequestMapping(value = "/allusers/{alluser}",name = "AllusersItemJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = AllusersItemJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = Alluser.class, type = ControllerType.ITEM)
@RooJSON
public class AllusersItemJsonController {

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
     * TODO Auto-generated constructor documentation
     * 
     * @param alluserService
     */
    @Autowired
    public AllusersItemJsonController(AlluserService alluserService) {
        this.alluserService = alluserService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Alluser
     */
    @ModelAttribute
    public Alluser getAlluser(@PathVariable("alluser") Long id) {
        Alluser alluser = alluserService.findOne(id);
        if (alluser == null) {
            throw new NotFoundException(String.format("Alluser with identifier '%s' not found",id));
        }
        return alluser;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute Alluser alluser) {
        return ResponseEntity.ok(alluser);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     * @return UriComponents
     */
    public static UriComponents showURI(Alluser alluser) {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(AllusersItemJsonController.class).show(alluser))
            .buildAndExpand(alluser.getId()).encode();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param storedAlluser
     * @param alluser
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute Alluser storedAlluser, @Valid @RequestBody Alluser alluser, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        alluser.setId(storedAlluser.getId());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(alluser.getPasswordHash());
        alluser.setPasswordHash(encodedPassword);
        getAlluserService().save(alluser);
        return ResponseEntity.ok().build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param alluser
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Alluser alluser) {
        getAlluserService().delete(alluser);
        return ResponseEntity.ok().build();
    }
}
