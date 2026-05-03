/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Yakup Yılmaz
 */
import java.sql.Connection;
import java.sql.DriverManager;


public abstract class DBConnect 
{
    Connection connect ;
    
    public Connection getConnect()
    {
        if(this.connect==null)
        {
            try 
            {
                Class.forName("org.postgresql.Driver");
                this.connect=DriverManager.getConnection("jdbc:postgresql://localhost:5432/asistanProject","postgres","12345");
            }
            catch (Exception e) 
            {
                System.out.println(e.getMessage());
            }
            
        }
        return connect ;
        
    }
}
