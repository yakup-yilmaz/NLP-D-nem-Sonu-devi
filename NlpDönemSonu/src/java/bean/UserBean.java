/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import dao.UserDAO;
import entity.SystemUser;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author Yakup Yılmaz
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private SystemUser entitiy;
    private UserDAO dao;
    private List<SystemUser> list;
    
    public void clearForm()
    {
        this.entitiy=new SystemUser();
    }
    
    public void create()
    {
        this.getDao().create(entitiy);
        this.entitiy=new SystemUser();
    }
    
     public void update()
    {
        this.getDao().update(entitiy);
        this.entitiy=new SystemUser();
    }
     
    public void delete()
    {
        this.getDao().delete(entitiy);
        this.entitiy=new SystemUser();
    }

    public SystemUser getEntitiy() 
    {   
        if(this.entitiy==null)
       {
         this.entitiy=new SystemUser();
       }
        return entitiy;
    }

    public void setEntitiy(SystemUser entitiy) 
    {   
        this.entitiy = entitiy;
    }

    public UserDAO getDao() 
    {   
       if(this.dao==null)
       {
         this.dao=new UserDAO();
       }
        return dao;
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public List<SystemUser> getList() 
    {   
        this.list=this.getDao().readList();
        return list;
    }

    public void setList(List<SystemUser> list) {
        this.list = list;
    }
    
   
     
    public UserBean() 
    {
        
    }
    
}
