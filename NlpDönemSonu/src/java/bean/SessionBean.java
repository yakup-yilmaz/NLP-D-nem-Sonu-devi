package bean;

import dao.NewsDAO;
import dao.UserDAO;
import entity.News;
import entity.SystemUser;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String pass;
    private SystemUser user;
    private UserDAO udao;

    public String getEmail() 
    { 
        return email; 
    }
    public void setEmail(String email) 
    { 
        this.email = email; 
    }

    public String getPass() 
    { 
        return pass; 
    }
    public void setPass(String pass) 
    {
        this.pass = pass; 
    }

    public SystemUser getUser() {
        
        if (this.user == null) 
        {
            Object sessionUser = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .get("user");
            if (sessionUser instanceof SystemUser) 
            {
                this.user = (SystemUser) sessionUser;
            }
        }
        return user;
    }

    public void setUser(SystemUser user) 
    {
        this.user = user;
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("user", user);
    }

    public UserDAO getUdao() 
    {
        if (this.udao == null) 
        {
            this.udao = new UserDAO();
        }
        return udao;
    }

    public void setUdao(UserDAO udao) 
    {
        this.udao = udao;
    }

    // Login metodu
    public String login() {
        SystemUser u = this.getUdao().getUser(email, pass);
        if (u != null) 
        {
            this.setUser(u); // hem bean hem sessionMap
            return "/panel/panel?faces-redirect=true";
        } else 
            
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Email veya şifre yanlış"));
            return null;
        }
    }

    // Logout metodu
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        this.user = null;
        return "/index.xhtml?faces-redirect=true";
    }
    
    public List<News> getUserNews() {
    if (this.user == null) return new ArrayList<>();
    NewsDAO ndao = new NewsDAO();
    return ndao.readListByUser(this.user);
}

}
