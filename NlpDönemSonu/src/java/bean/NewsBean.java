/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import dao.NewsDAO;
import entity.News;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Yakup Yılmaz
 */
@Named(value = "newsBean")
@SessionScoped
public class NewsBean implements Serializable {

     private News entity;
     private List<News> list;
     private NewsDAO ndao;
     private int page=1;
     private int pageCount=0;
    
    
    public NewsBean() 
    {
        
    }

    public News getEntity() 
    {   
        if(this.entity==null)
      {
        this.entity=new News();
      }
        return entity;
    }

    public void setEntity(News entity) {
        this.entity = entity;
    }

    public List<News> getList() 
    {   this.list=this.getNdao().readList(page);
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }

    public NewsDAO getNdao() 
    {   if(this.ndao==null)
       {
         this.ndao=new NewsDAO();
       }
        return ndao;
    }

    public void setNdao(NewsDAO ndao) {
        this.ndao = ndao;
    }
    
    public void create()
    {
        this.getNdao().create(entity);
        entity=new News();
    }
    
    public void update()
    {
        this.getNdao().update(entity);
        this.entity=new News();
    }
    
    public void clearForm()
    {
        this.entity=new News();
    }
    
    public void delete()
    {
        this.getNdao().delete(entity);
        this.entity=new News();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
    List<News> glist=this.getNdao().readList();
        int size=glist.size();
        pageCount=(int)Math.ceil(size/2.0);
        return pageCount;    }

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
