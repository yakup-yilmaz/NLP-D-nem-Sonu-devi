/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import dao.CategoryDAO;
import entity.Category;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Yakup Yılmaz
 */
@Named(value = "categoryBean")
@SessionScoped
public class CategoryBean implements Serializable {

    private Category entitiy;
    private CategoryDAO dao;
    private List<Category> list;
    
    public void clearForm()
    {
        this.entitiy=new Category();
    }
    
    public void create()
    {
        this.getDao().create(entitiy);
        this.entitiy=new Category();
    }
    
     public void update()
    {
        this.getDao().update(entitiy);
        this.entitiy=new Category();
    }
     
    public void delete()
    {
        this.getDao().delete(entitiy);
        this.entitiy=new Category();
    }

    public Category getEntitiy() 
    {   
        if(this.entitiy==null)
       {
         this.entitiy=new Category();
       }
        return entitiy;
    }

    public void setEntitiy(Category entitiy) {
        this.entitiy = entitiy;
    }

    public CategoryDAO getDao() 
    {   
       if(this.dao==null)
       {
         this.dao=new CategoryDAO();
       }
        return dao;
    }

    public void setDao(CategoryDAO dao) {
        this.dao = dao;
    }

    public List<Category> getList() 
    {   
        this.list=this.getDao().readList();
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }
     
    public CategoryBean() 
    {
        
    }
    
}
