/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category;
import entity.SystemGroup;
import entity.SystemUser;
import java.util.ArrayList;
import java.util.List;
import util.DBConnect;
import java.sql.Statement;
import java.sql.ResultSet;



public class UserDAO extends DBConnect
{    
    GroupDAO gdao;
    
    
    
    
    public List<SystemUser> readList()
    {
        List <SystemUser> list = new ArrayList<>();
        
        try 
        {
            Statement st=this.getConnect().createStatement();
            ResultSet rs= st.executeQuery("select * from systemusers order by id asc");
            while(rs.next())
            { SystemGroup g= this.getGdao().getById(rs.getLong("ugroup"));
               
              list.add(new SystemUser(rs.getLong("id"),rs.getString("username"),rs.getString("lastName"),
         rs.getString("email"), rs.getString("pass"), g,this.getUserCategories(rs.getLong("id"))) );            
            }
        } 
        
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        } 
        return list;
    }
    
    public void create(SystemUser c)
    {
        
        try 
        {
            Statement st = this.getConnect().createStatement();
            st.executeUpdate("insert into systemusers(email,pass,ugroup,username,lastName) values('"+c.getEmail()+"','"+c.getPass()+"',"+c.getGroup().getId()+",'"+c.getUserName()+"','"+c.getLastName()+"')");
            ResultSet rs=st.executeQuery("select max(id) as mid from systemusers");
            rs.next();
            Long user_id=rs.getLong("mid");
            
            for(Category cat:c.getCategory())
            {
                String query="insert into user_news_category(user_id,category_id) values("+user_id+","+cat.getId()+")";
                st.executeUpdate(query);
            }
        }
        catch (Exception e) 
        {
              e.printStackTrace();
        }
    }
    
    public void update(SystemUser c)
    {
         // mail güncelleme yasak   
        try 
        {
            Statement st = this.getConnect().createStatement();
            st.executeUpdate("update systemusers set pass='"+c.getPass()+"' ,username='"+c.getUserName()+"',lastname='"+c.getLastName()+"', ugroup="+c.getGroup().getId()+" where id="+c.getId());
            st.executeUpdate("delete from user_news_category where user_id="+c.getId());
             
              for(Category cat:c.getCategory())
            {
                String query="insert into user_news_category(user_id,category_id) values("+c.getId()+","+cat.getId()+")";
                st.executeUpdate(query);
            }

        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(SystemUser c)
    {
        
        try 
        {
            Statement st = this.getConnect().createStatement();
            st.executeUpdate("delete from user_news_category where user_id="+c.getId());
            st.executeUpdate("delete from systemusers where id="+c.getId());
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    
     public List<Category> getUserCategories(Long id )
      {
          
          List<Category> list = new ArrayList<>();
          try 
         {
            Statement st = this.getConnect().createStatement();
            String query=
            "select * from news_category where id in(select category_id from user_news_category where user_id="+id+")";
                    
            ResultSet rs=st.executeQuery(query);
            
            while(rs.next())
            {
             list.add(new Category(rs.getLong("id"),rs.getString("title"),rs.getDate("updated"),rs.getDate("created")));
            }
  
         }
          
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
          return list;
            
      }
      

    public GroupDAO getGdao() 
    {   
        if(this.gdao==null)
         {
           this.gdao=new GroupDAO();
         }
        return gdao;
    }

    public void setGdao(GroupDAO gdao) {
        this.gdao = gdao;
    }
    
   public SystemUser getUser(String email, String pass) {
    System.out.println("LOGIN DENEMESİ -> email: " + email + " pass: " + pass);

    SystemUser u = null;
    try {
        Statement st = this.getConnect().createStatement();
        ResultSet rs = st.executeQuery(
            "select * from systemusers where email='" + email + "' and pass='" + pass + "'"
        );

        if (rs.next()) {
            System.out.println("KULLANICI BULUNDU");
            SystemGroup g = this.getGdao().getById(rs.getLong("ugroup"));
            u = new SystemUser(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("pass"),
                g,
                this.getUserCategories(rs.getLong("id"))
            );
        } else {
            System.out.println("KULLANICI BULUNAMADI ❌");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return u;
}
   
   
    
    
}
