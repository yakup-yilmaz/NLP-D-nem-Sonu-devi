/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Converter;

import dao.CategoryDAO;
import entity.Category;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author Yakup Yılmaz
 */

@FacesConverter("categoryConverter")
public class CategoryConverter implements Converter
{ 
     CategoryDAO cdao;

    public CategoryDAO getCdao() 
    {    
        if(this.cdao==null)
        {
            this.cdao=new CategoryDAO();
        } 
        return cdao;
    }

    public void setCdao(CategoryDAO cdao) {
        this.cdao = cdao;
    }
   
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        Long id=Long.valueOf(value);
        return this.getCdao().getById(id);
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        Category cate= (Category) value;
        return String.valueOf(cate.getId());
        
    }
    
}
