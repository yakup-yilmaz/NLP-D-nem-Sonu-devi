/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Yakup Yılmaz
 */
import entity.SystemGroup;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnect;

public class GroupDAO extends DBConnect
{
    
    
    
     public SystemGroup getById(Long id)
   {
       SystemGroup sg=null;
         try
          {
            Statement st=this.getConnect().createStatement();
            ResultSet rs= st.executeQuery("select * from systemgroup where id="+id);
            rs.next();            
            sg=new SystemGroup(rs.getLong("id"), rs.getString("gname"), rs.getDate("created"), rs.getDate("updated"));
          }
          catch(Exception e)
          {
              System.out.println(e.getMessage());
          } 
        return sg;
    }
    
      public void create(SystemGroup c)
      {
            try
            {
                Statement st=this.getConnect().createStatement();
                String query="insert into systemgroup(gname) values('"+c.getGname()+"')" ;
                st.executeUpdate(query);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            
      }
      
      
      public void update(SystemGroup c)
      {
              try
                  {
                      Statement st=this.getConnect().createStatement();
                      st.executeUpdate("update systemgroup set gname='"+c.getGname()+"' where id="+c.getId());
                  }
              
              catch(Exception e)
              {
                  System.out.println(e.getMessage());
              }
      }
      
      public void delete(SystemGroup c)
      {
          try 
        {
            Statement st = this.getConnect().createStatement();
            st.executeUpdate("delete from systemgroup where id="+c.getId());
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
      }
      
      
      // burda da ben sayfalama yapıyorum o yüzden bu parametreli metodu kullanmam grekiyor
      public List<SystemGroup> readList(int page)
      {
          int offset=(page-1)*5;
          List <SystemGroup> list = new ArrayList<>();
        
        try 
        {
            Statement st=this.getConnect().createStatement();
            ResultSet rs= st.executeQuery("select * from systemgroup limit 5 offset "+offset);
            while(rs.next())
            {
              list.add(new SystemGroup
     (rs.getLong("id"), rs.getString("gname"), rs.getDate("updated"), rs.getDate("created")));
            }
        } 
        
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        } 
        return list;
      }
      
      // kullandığım yer mesela kullanıcılara group seçtiriyorum ya orda hepsini tek parça göstermem gerek 
      public List<SystemGroup> readList()
      {
          
          List <SystemGroup> list = new ArrayList<>();
        
        try 
        {
            Statement st=this.getConnect().createStatement();
            ResultSet rs= st.executeQuery("select * from systemgroup");
            while(rs.next())
            {
              list.add(new SystemGroup
     (rs.getLong("id"), rs.getString("gname"), rs.getDate("updated"), rs.getDate("created")));
            }
        } 
        
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        } 
        return list;
      }
}
