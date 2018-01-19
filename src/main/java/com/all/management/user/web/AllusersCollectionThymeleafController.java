package com.all.management.user.web;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import com.all.management.user.model.Alluser;
import com.all.management.user.repository.AlluserRepository;
import com.all.management.user.service.api.AlluserService;
import com.all.management.user.web.reports.ExportingErrorException;
import com.all.management.user.web.reports.JasperReportsCsvExporter;
import com.all.management.user.web.reports.JasperReportsExporter;
import com.all.management.user.web.reports.JasperReportsPdfExporter;
import com.all.management.user.web.reports.JasperReportsXlsExporter;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.datatables.ConvertedDatatablesData;
import io.springlets.data.web.datatables.Datatables;
import io.springlets.data.web.datatables.DatatablesColumns;
import io.springlets.data.web.datatables.DatatablesPageable;
import io.springlets.data.web.select2.Select2DataSupport;
import io.springlets.data.web.select2.Select2DataWithConversion;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;

@Controller
@RequestMapping(value = "/allusers",name = "AllusersCollectionThymeleafController",produces = MediaType.TEXT_HTML_VALUE)
/**
 * = AllusersCollectionThymeleafController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = Alluser.class, type = ControllerType.COLLECTION)
@RooThymeleaf
public class AllusersCollectionThymeleafController {

	public AllusersCollectionThymeleafController() {
		super();
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
     * TODO Auto-generated attribute documentation
     * 
     */
    private ConversionService conversionService;
    
    @Autowired
	private AlluserRepository alluserRepository;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param alluserService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public AllusersCollectionThymeleafController(AlluserService alluserService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setAlluserService(alluserService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(AllusersItemThymeleafController.class));
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
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "list")
    public ModelAndView list(Model model) {
        return new ModelAndView("allusers/list");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(produces = Datatables.MEDIA_TYPE, name = "datatables", value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Alluser>> datatables(DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Alluser> allusers = getAlluserService().findAll(search, pageable);
        long totalAllusersCount = allusers.getTotalElements();
        if (search != null && StringUtils.isNotBlank(search.getText())) {
            totalAllusersCount = getAlluserService().count();
        }
        ConvertedDatatablesData<Alluser> datatablesData = new ConvertedDatatablesData<Alluser>(allusers, totalAllusersCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(datatablesData);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(produces = Datatables.MEDIA_TYPE, name = "datatablesByIdsIn", value = "/dtByIdsIn")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Alluser>> datatablesByIdsIn(@RequestParam("ids") List<Long> ids, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Alluser> allusers = getAlluserService().findAllByIdsIn(ids, search, pageable);
        long totalAllusersCount = allusers.getTotalElements();
        if (search != null && StringUtils.isNotBlank(search.getText())) {
            totalAllusersCount = getAlluserService().count();
        }
        ConvertedDatatablesData<Alluser> datatablesData = new ConvertedDatatablesData<Alluser>(allusers, totalAllusersCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(datatablesData);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param search
     * @param pageable
     * @param locale
     * @return ResponseEntity
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<Alluser>> select2(GlobalSearch search, Pageable pageable, Locale locale) {
        Page<Alluser> allusers = getAlluserService().findAll(search, pageable);
        String idExpression = "#{id}";
        Select2DataSupport<Alluser> select2Data = new Select2DataWithConversion<Alluser>(allusers, idExpression, getConversionService());
        return ResponseEntity.ok(select2Data);
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
     * @param result
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@Valid @ModelAttribute Alluser alluser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            populateForm(model);
            
            return new ModelAndView("allusers/create");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(alluser.getPasswordHash());
        alluser.setPasswordHash(encodedPassword);
        
        Alluser newAlluser = getAlluserService().save(alluser);
        UriComponents showURI = getItemLink().to(AllusersItemThymeleafLinkFactory.SHOW).with("alluser", newAlluser.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(Model model) {
        populateForm(model);
        
        model.addAttribute("alluser", new Alluser());
        return new ModelAndView("allusers/create");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    @ResponseBody
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        
        getAlluserService().delete(ids);
        
        return ResponseEntity.ok().build();
    }

	/**
     * Method that obtains the filtered and ordered records using the Datatables information and 
     * export them to a new report file. (It ignores the current pagination).
     * 
     * To generate the report file it uses the `DynamicJasper` library
     * (http://dynamicjasper.com). This library allows developers to generate reports dynamically
     * without use an specific template to each entity.
     * 
     * To customize the appearance of ALL generated reports, you could customize the 
     * "export_default.jrxml" template located in "src/main/resources/templates/reports/". However,
     * if you want to customize the appearance of this specific report, you could create a new
     * ".jrxml" file and provide it to the library replacing the `builder.setTemplateFile();`
     * operation used in this implementation.
     * 
     * @param search GlobalSearch that contains the filter provided by the Datatables component.
     * @param pageable Pageable that contains the Sort info provided by the Datatabes component.
     * @param datatablesColumns Columns displayed in the Datatables component.
     * @param response The HttpServletResponse.
     * @param exporter An specific JasperReportsExporter to be used during export process.
     * @param fileName The final filename to use.
     * @param locale The current Locale in the view context.
     */
    public void export(GlobalSearch search, @PageableDefault(size = 2147483647) Pageable pageable, String[] datatablesColumns, HttpServletResponse response, JasperReportsExporter exporter, String fileName, Locale locale) {
        // Obtain the filtered and ordered elements
        Page<Alluser> allusers = getAlluserService().findAll(search, pageable);
        
        // Prevent generation of reports with empty data
        if (allusers == null || allusers.getContent().isEmpty()) {
            return;
        }
        
        // Creates a new ReportBuilder using DynamicJasper library
        FastReportBuilder builder = new FastReportBuilder();
        
        // IMPORTANT: By default, this application uses "export_default.jrxml"
        // to generate all reports. If you want to customize this specific report,
        // create a new ".jrxml" template and customize it. (Take in account the 
        // DynamicJasper restrictions: 
        // http://dynamicjasper.com/2010/10/06/how-to-use-custom-jrxml-templates/)
        builder.setTemplateFile("templates/reports/export_default.jrxml");
        
        // The generated report will display the same columns as the Datatables component.
        // However, this is not mandatory. You could edit this code if you want to ignore
        // the provided datatablesColumns
        if (datatablesColumns != null) {
            for (String column : datatablesColumns) {
                // Delegates in addColumnToReportBuilder to include each datatables column
                // to the report builder
                addColumnToReportBuilder(column, builder, locale, fileName);
            }
        }
        
        // This property resizes the columns to use full width page.
        // Set false value if you want to use the specific width of each column.
        builder.setUseFullPageWidth(true);
        
        // Creates a new Jasper Reports Datasource using the obtained elements
        JRDataSource ds = new JRBeanCollectionDataSource(allusers.getContent());
        
        // Generates the JasperReport
        JasperPrint jp;
        try {
            jp = DynamicJasperHelper.generateJasperPrint(builder.build(), new ClassicLayoutManager(), ds);
        }
        catch (JRException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException", 
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()}, 
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }
        
        // Converts the JaspertReport element to a ByteArrayOutputStream and
        // write it into the response stream using the provided JasperReportExporter
        try {
            exporter.export(jp, fileName, response);
        }
        catch (JRException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException", 
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()}, 
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }
        catch (IOException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException", 
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()}, 
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }
    }

	/**
     * It delegates in the `export` method providing the necessary information
     * to generate a CSV report.
     * 
     * @param search The GlobalSearch that contains the filter provided by the Datatables component
     * @param pageable The Pageable that contains the Sort info provided by the Datatabes component
     * @param datatablesColumns The Columns displayed in the Datatables component
     * @param response The HttpServletResponse
     * @return ResponseEntity
     */
    @GetMapping(name = "exportCsv", value = "/export/csv")
    @ResponseBody
    public ResponseEntity<?> exportCsv(GlobalSearch search, @PageableDefault(size = 2147483647) Pageable pageable, @RequestParam("datatablesColumns") String[] datatablesColumns, HttpServletResponse response, Locale locale) {
        export(search, pageable, datatablesColumns, response, new JasperReportsCsvExporter(), "allusers_report.csv", locale);
        return ResponseEntity.ok().build();
    }

	/**
     * It delegates in the `export` method providing the necessary information
     * to generate a PDF report.
     * 
     * @param search The GlobalSearch that contains the filter provided by the Datatables component
     * @param pageable The Pageable that contains the Sort info provided by the Datatabes component
     * @param datatablesColumns The Columns displayed in the Datatables component
     * @param response The HttpServletResponse
     * @return ResponseEntity
     */
    @GetMapping(name = "exportPdf", value = "/export/pdf")
    @ResponseBody
    public ResponseEntity<?> exportPdf(GlobalSearch search, @PageableDefault(size = 2147483647) Pageable pageable, @RequestParam("datatablesColumns") String[] datatablesColumns, HttpServletResponse response, Locale locale) {
        export(search, pageable, datatablesColumns, response, new JasperReportsPdfExporter(), "allusers_report.pdf", locale);
        return ResponseEntity.ok().build();
    }

	/**
     * It delegates in the `export` method providing the necessary information
     * to generate a XLS report.
     * 
     * @param search The GlobalSearch that contains the filter provided by the Datatables component
     * @param pageable The Pageable that contains the Sort info provided by the Datatabes component
     * @param datatablesColumns The Columns displayed in the Datatables component
     * @param response The HttpServletResponse
     * @return ResponseEntity
     */
    @GetMapping(name = "exportXls", value = "/export/xls")
    @ResponseBody
    public ResponseEntity<?> exportXls(GlobalSearch search, @PageableDefault(size = 2147483647) Pageable pageable, @RequestParam("datatablesColumns") String[] datatablesColumns, HttpServletResponse response, Locale locale) {
        export(search, pageable, datatablesColumns, response, new JasperReportsXlsExporter(), "allusers_report.xls", locale);
        return ResponseEntity.ok().build();
    }

	/**
     * This method contains all the entity fields that are able to be displayed in a 
     * report. The developer could add a new column to the report builder providing the 
     * field name and the builder where the new field will be added as column.
     * 
     * @param columnName the field name to show as column
     * @param builder The builder where the new field will be added as column.
     */
    public void addColumnToReportBuilder(String columnName, FastReportBuilder builder, Locale locale, String fileName) {
        try {
        if (columnName.equals("id")) {
            builder.addColumn(getMessageSource().getMessage("label_alluser_id", null, "Id", locale), "id", Long.class.getName(), 50);
        }
        else if (columnName.equals("version")) {
            builder.addColumn(getMessageSource().getMessage("label_alluser_version", null, "Version", locale), "version", Integer.class.getName(), 100);
        }
        else if (columnName.equals("loginId")) {
            builder.addColumn(getMessageSource().getMessage("label_alluser_loginid", null, "Login Id", locale), "loginId", String.class.getName(), 100);
        }
        else if (columnName.equals("passwordHash")) {
            builder.addColumn(getMessageSource().getMessage("label_alluser_passwordhash", null, "Password Hash", locale), "passwordHash", String.class.getName(), 100);
        }
        else if (columnName.equals("email")) {
            builder.addColumn(getMessageSource().getMessage("label_alluser_email", null, "Email", locale), "email", String.class.getName(), 100);
        }
        else if (columnName.equals("appRole")) {
            builder.addColumn(getMessageSource().getMessage("label_alluser_approle", null, "App Role", locale), "appRole", String.class.getName(), 100);
        }
        else if (columnName.equals("userActive")) {
            builder.addColumn(getMessageSource().getMessage("label_alluser_useractive", null, "User Active", locale), "userActive", Boolean.class.getName(), 100);
        }
        else if (columnName.equals("lastUpdateDate")) {
            builder.addColumn(getMessageSource().getMessage("label_alluser_lastupdatedate", null, "Last Update Date", locale), "lastUpdateDate", Calendar.class.getName(), 100);
        }
        }
        catch (ColumnBuilderException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException", 
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()}, 
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }
        catch (ClassNotFoundException e) {
            String errorMessage = getMessageSource().getMessage("error_exportingErrorException", 
                new Object[] {StringUtils.substringAfterLast(fileName, ".").toUpperCase()}, 
                String.format("Error while exporting data to StringUtils file", StringUtils.
                    substringAfterLast(fileName, ".").toUpperCase()), locale);
            throw new ExportingErrorException(errorMessage);
        }
    }

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
    
    /*@ModelAttribute
    public Alluser getLoginuser() {
        Alluser loginuser = null;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loginName = auth.getName(); //get logged in username

        
        loginuser = alluserRepository.findByLoginId(loginName);
        
        return loginuser;
    }*/
    
    @GetMapping(value = "/profile", name = "profile")
    public ModelAndView showProfile(Model model) {
    	populateForm(model);
    	
    	Alluser loginuser = null;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loginName = auth.getName(); //get logged in username

        
        loginuser = alluserRepository.findByLoginId(loginName);
    	if(loginuser != null) {
    		model.addAttribute("loginuser", loginuser);
    		return new ModelAndView("allusers/profile");
    	}
    	else {
    		//go to an error page
    		return new ModelAndView("error");
    	}
    }
    
}
