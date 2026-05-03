/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import dao.GroupDAO;
import entity.SystemGroup;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yakup Yılmaz
 */
@Named(value = "groupBean")
@SessionScoped
public class GroupBean implements Serializable {

    private SystemGroup entitiy;
    private GroupDAO dao;
    private List<SystemGroup> list;
    private int page=1;
    private int pageCount=0;
    
    
    
    public GroupBean() 
    {
        
    }
    
      public void clearForm()
    {
        this.entitiy=new SystemGroup();
    }
    
    public void create()
    {
        this.getDao().create(entitiy);
        this.entitiy=new SystemGroup();
    }
    
     public void update()
    {
        this.getDao().update(entitiy);
        this.entitiy=new SystemGroup();
    }
     
    public void delete()
    {
        this.getDao().delete(entitiy);
        this.entitiy=new SystemGroup();
    }
    

    public SystemGroup getEntitiy() 
    {    if(this.entitiy==null)
       {
           this.entitiy= new SystemGroup();
       }
        return entitiy;
    }

    public void setEntitiy(SystemGroup entitiy) {
        this.entitiy = entitiy;
    }

    public GroupDAO getDao() 
    {
        if(this.dao==null)
        {
            this.dao= new GroupDAO();
        }
        return dao;
    }

    public void setDao(GroupDAO dao) {
        this.dao = dao;
    }

    public List<SystemGroup> getList() 
    {   this.list= this.getDao().readList(page);
        return list;
    }

    public void setList(List<SystemGroup> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() 
    {   
        List<SystemGroup> glist=this.getDao().readList();
        int size=glist.size();
        pageCount=(int)Math.ceil(size/5.0);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    public void next()
    {
        page++;
        
        if(page>this.getPageCount())
        {
            page=1;
        }
         
    }
    
    public void previous()
    {
        page--;
        if(page<1)
        {
            page=this.getPageCount();
        }
        
    }

    
    
    
    
}
