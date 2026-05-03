/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category;
import java.util.ArrayList;
import java.util.List;
import util.DBConnect;
import java.sql.Statement;
import java.sql.ResultSet;



public class CategoryDAO extends DBConnect
{
    
    
     public Category getById(Long id)
   {
       Category cate=null;
         try
          {
            Statement st=this.getConnect().createStatement();
            ResultSet rs= st.executeQuery("select * from news_category where id="+id);
            rs.next();            
            cate=new Category(rs.getLong("id"), rs.getString("title"), rs.getDate("updated"), rs.getDate("created"));
          }
          catch(Exception e)
          {
              System.out.println(e.getMessage());
          } 
        return cate;
    }
    
    
    
    public List<Category> readList()
    {
        List <Category> list = new ArrayList<>();
        
        try 
        {
            Statement st=this.getConnect().createStatement();
            ResultSet rs= st.executeQuery("select * from news_category");
            while(rs.next())
            {
              list.add(new Category
     (rs.getLong("id"), rs.getString("title"), rs.getDate("updated"), rs.getDate("created")));
            }
        } 
        
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        } 
        return list;
    }
    
    public void create(Category c)
    {
        
        try 
        {
            Statement st = this.getConnect().createStatement();
            st.executeUpdate("insert into news_category(title) values('"+c.getTitle()+"')");
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Category c)
    {
        
        try 
        {
            Statement st = this.getConnect().createStatement();
            st.executeUpdate("update news_category set title='"+c.getTitle()+"' where id="+c.getId());
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(Category c)
    {
        
        try 
        {
            Statement st = this.getConnect().createStatement();
            st.executeUpdate("delete from news_category where id="+c.getId());
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public CategoryDAO()
    {
        
    }
}
