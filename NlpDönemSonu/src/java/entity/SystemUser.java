/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yakup Yılmaz
 */
public class SystemUser implements Serializable
{
  private Long id;
  private String userName;
  private String lastName;
  private String email;
  private String pass;
  private SystemGroup group;
  private List<Category> category;

    public SystemUser() 
    {
        
    }

    public SystemUser(Long id, String userName, String lastName, String email, String pass, SystemGroup group, List<Category> category) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.pass = pass;
        this.group = group;
        this.category = category;
    }
    
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public SystemGroup getGroup() 
    {   if(this.group==null)
       {
          this.group=new SystemGroup();
       }
        return group;
    }

    public void setGroup(SystemGroup group) {
        this.group = group;
    }

    public List<Category> getCategory() 
    {   if(category==null)
       {
         this.category=new ArrayList<>(); 
       }
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   
    
    
    
  
}
