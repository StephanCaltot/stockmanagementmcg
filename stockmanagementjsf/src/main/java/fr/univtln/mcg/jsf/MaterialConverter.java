package fr.univtln.mcg.jsf;

/**
 * Created by jlng on 07/12/16.
 */

import fr.univtln.mcg.material.Material;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("materialConverter")
public class MaterialConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                MaterialService service = (MaterialService) fc.getExternalContext().getApplicationMap().get("materialService");
                return service.getList().get(Integer.parseInt(value)-1);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Material) object).getId());
        }
        else {
            return "test";
        }
    }
}