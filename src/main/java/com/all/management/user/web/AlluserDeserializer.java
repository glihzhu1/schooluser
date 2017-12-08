package com.all.management.user.web;
import com.all.management.user.model.Alluser;
import com.all.management.user.service.api.AlluserService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;

@JsonComponent
/**
 * = AlluserDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Alluser.class)
public class AlluserDeserializer extends JsonObjectDeserializer<Alluser> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private AlluserService alluserService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param alluserService
     * @param conversionService
     */
    @Autowired
    public AlluserDeserializer(@Lazy AlluserService alluserService, ConversionService conversionService) {
        this.alluserService = alluserService;
        this.conversionService = conversionService;
    }

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
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return Alluser
     * @throws IOException
     */
    public Alluser deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Alluser alluser = alluserService.findOne(id);
        if (alluser == null) {
            throw new NotFoundException("Alluser not found");
        }
        return alluser;
    }
}
