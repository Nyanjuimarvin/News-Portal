package Dao;

import Models.CompanyNews;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class CompanyNewsImplementation implements CompanyNewsDao{

    private final Sql2o sql2o;
    public CompanyNewsImplementation(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(CompanyNews companyNews) {
        String sql = "INSERT INTO news (information,category,datecreated,readabledate,type) VALUES (:information,:category,:dateCreated,:readableDate,:type)";
        try(Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql,true)
                    .addParameter("information",companyNews.getInformation())
                    .addParameter("category",companyNews.getCategory())
                    .addParameter("dateCreated",companyNews.getDateCreated())
                    .addParameter("readableDate",companyNews.getReadableDate())
                    .addParameter("type",companyNews.type)
                    .bind(companyNews)
                    .executeUpdate()
                    .getKey();
            companyNews.setId(id);
        }

    }

    @Override
    public CompanyNews getById(int id) {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM news WHERE id = :id AND type = 'company'")
                    .addParameter("id",id)
                    .executeAndFetchFirst(CompanyNews.class);
        }
    }

    @Override
    public List<CompanyNews> allCompanyNews() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM news WHERE type = 'company'")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(CompanyNews.class);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM news WHERE id = :id")
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteAllCompanyNews() {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM news WHERE type = 'company'")
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }
}
