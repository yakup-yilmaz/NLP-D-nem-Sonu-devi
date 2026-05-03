package dao;

import entity.Category;
import entity.News;
import entity.SystemUser;
import util.DBConnect;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.AiService; 

public class NewsDAO extends DBConnect {

    private CategoryDAO cdao;

    public void create(News c) {
        try {
            // 1. Önce Python'dan özeti alalım
            String aiSummary = AiService.getSummary(c.getText());
            c.setSummary(aiSummary);

            // 2. SQL Tırnak hatasını önlemek için metinleri temizleyelim
            String safeTitle = c.getTitle().replace("'", "''");
            String safeContent = c.getText().replace("'", "''");
            String safeSummary = c.getSummary().replace("'", "''");

            // 3. SQL sorgusuna 'summary' ekleyelim
            Statement st = this.getConnect().createStatement();
            String query = "insert into news(title,content,category_id,summary) values('"
                    + safeTitle + "','"
                    + safeContent + "',"
                    + c.getCategory().getId() + ",'"
                    + safeSummary + "')"; 
            
            st.executeUpdate(query);
            System.out.println("✅ AI Özetli Haber Başarıyla Kaydedildi.");
        } catch (Exception e) {
            System.out.println("Create Hatası (Detay): " + e.getMessage());
            e.printStackTrace(); // Tam hatayı konsolda görmek için
        }
    }

    public void update(News c) 
    {
    
        try 
        {
        // Haber metni değişmiş olabilir, özeti de yeniden alalım
        String aiSummary = AiService.getSummary(c.getText());
        c.setSummary(aiSummary);

        // SQL Tırnak temizliği
        String safeTitle = c.getTitle().replace("'", "''");
        String safeContent = c.getText().replace("'", "''");
        String safeSummary = c.getSummary().replace("'", "''");

        Statement st = this.getConnect().createStatement();
        
        // Sorguya category_id ekledik:
        String query = "update news set title='" + safeTitle 
                + "' , content='" + safeContent 
                + "' , summary='" + safeSummary 
                + "' , category_id=" + c.getCategory().getId() // Bu satırı ekledik
                + " where id=" + c.getId();
        
        st.executeUpdate(query);
        System.out.println("✅ Haber ve Kategorisi başarıyla güncellendi.");
        }
        
        catch (Exception e) 
      {
        System.out.println("Update Hatası: " + e.getMessage());
      }
        
    }

    // ... Diğer readList ve delete metodların aynı kalabilir ...
    
    public void delete(News c) {
        try {
            Statement st = this.getConnect().createStatement();
            String query = "delete from news where id=" + c.getId();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<News> readList() {
        List<News> list = new ArrayList<>();
        try {
            Statement st = this.getConnect().createStatement();
            ResultSet rs = st.executeQuery("select * from news");
            while (rs.next()) {
                Category g = this.getCdao().getById(rs.getLong("category_id"));
                News n = new News(rs.getLong("id"), rs.getString("title"), rs.getString("content"), g, rs.getDate("created"), rs.getDate("updated"));
                n.setSummary(rs.getString("summary"));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<News> readList(int page) {
        int offset = (page - 1) * 2;
        List<News> list = new ArrayList<>();
        try {
            Statement st = this.getConnect().createStatement();
            ResultSet rs = st.executeQuery("select * from news limit 2 offset " + offset);
            while (rs.next()) {
                Category g = this.getCdao().getById(rs.getLong("category_id"));
                News n = new News(rs.getLong("id"), rs.getString("title"), rs.getString("content"), g, rs.getDate("created"), rs.getDate("updated"));
                n.setSummary(rs.getString("summary"));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<News> readListByUser(SystemUser user) {
        List<News> list = new ArrayList<>();
        try {
            Statement st = this.getConnect().createStatement();
            String categories = user.getCategory().stream()
                    .map(c -> c.getId().toString())
                    .reduce((a, b) -> a + "," + b)
                    .orElse("0");

            ResultSet rs = st.executeQuery("SELECT * FROM news WHERE category_id IN (" + categories + ")");

            while (rs.next()) {
                Category cat = this.getCdao().getById(rs.getLong("category_id"));
                News news = new News(rs.getLong("id"), rs.getString("title"), rs.getString("content"), cat, rs.getDate("created"), rs.getDate("updated"));
                news.setSummary(rs.getString("summary")); 
                list.add(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public CategoryDAO getCdao() {
        if (this.cdao == null) { this.cdao = new CategoryDAO(); }
        return cdao;
    }

    public void setCdao(CategoryDAO cdao) { this.cdao = cdao; }
}