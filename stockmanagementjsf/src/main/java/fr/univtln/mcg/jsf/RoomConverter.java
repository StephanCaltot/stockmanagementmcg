package fr.univtln.mcg.jsf;

/**
 * Created by jlng on 07/12/16.
 */

import fr.univtln.mcg.Room;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("roomConverter")
public class RoomConverter implements Converter{

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                RoomService service = (RoomService) fc.getExternalContext().getApplicationMap().get("roomService");
                return service.getList().get(Integer.parseInt(value)-1);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Room) object).getId());
        }
        else {
            return null;
        }
    }
}