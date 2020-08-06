package br.com.suga.converter;

import br.com.suga.entity.Cliente;
import org.apache.commons.lang3.StringUtils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * The type Cliente converter.
 */
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value)
    {
        if (StringUtils.isNotBlank(value))
        {
            return uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value)
    {
        if (value instanceof Cliente)
        {
            Cliente entity = (Cliente) value;
            if (entity instanceof Cliente)
            {
                uiComponent.getAttributes().put(String.valueOf(entity.getId()), entity);
                return String.valueOf(entity.getId());
            }
        }
        return "";
    }
}
