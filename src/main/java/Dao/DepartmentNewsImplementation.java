package Dao;

import Models.Department;
import Models.DepartmentNews;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class DepartmentNewsImplementation implements DepartmentNewsDao{

    private final Sql2o sql2o;
    public DepartmentNewsImplementation(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(DepartmentNews departmentNews) {
        String sql = "INSERT INTO news (information,category,datecreated,humandate,type) VALUES (:information,:category,:dateCreated,:readableDate,:type)";
        try(Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql,true)
                    .addParameter("information",departmentNews.getInformation())
                    .addParameter("category",departmentNews.getCategory())
                    .addParameter("dateCreated",departmentNews.getDateCreated())
                    .addParameter("readableDate",departmentNews.getReadableDate())
                    .addParameter("type",departmentNews.type)
                    .bind(departmentNews)
                    .executeUpdate()
                    .getKey();
            departmentNews.setId(id);
        }
    }

    @Override
    public void addDepartmentNews(Department department, DepartmentNews departmentNews) {

        try(Connection conn = sql2o.open()) {
            conn.createQuery("INSERT INTO department_news (departmentid,newsid) VALUES (:departmentId,:newsId)")
                    .addParameter("departmentId",department.getId())
                    .addParameter("newsId", departmentNews.getId())
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public DepartmentNews getById(int id) {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM news WHERE id = :id AND type = 'department'")
                    .addParameter("id",id)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }

    @Override
    public List<DepartmentNews> getAll() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM news WHERE type = 'department'")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(DepartmentNews.class);
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
    public void deleteDepartmentNews() {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM news WHERE type = 'department'")
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }
}
