package br.com.suga.converter;

import br.com.suga.entity.Produto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Produto converter.
 */
@FacesConverter("produtoConverter")
public class ProdutoConverter implements Converter {

    Logger logger = Logger.getLogger(ProdutoConverter.class.getName());

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
        if (value instanceof Produto)
        {
            Produto entity = (Produto) value;
            if (entity instanceof Produto)
            {
                uiComponent.getAttributes().put(String.valueOf(entity.getId()), entity);
                return String.valueOf(entity.getId());
            }
        }
        return "";
    }
}