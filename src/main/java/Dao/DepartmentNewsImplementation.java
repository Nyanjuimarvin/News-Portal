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
        String sql = "INSERT INTO news (information,category,datecreated,type) VALUES (:information,:category,:dateCreated,:type)";
        try(Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql,true)
                    .addParameter("information",departmentNews.getInformation())
                    .addParameter("category",departmentNews.getCategory())
                    .addParameter("dateCreated",departmentNews.getDateCreated())
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
        return null;
    }

    @Override
    public DepartmentNews getAll() {
        return null;
    }

    @Override
    public List<DepartmentNews> allCompanyNews() {
        return null;
    }

    @Override
    public DepartmentNews deleteById(int id) {
        return null;
    }

    @Override
    public void deleteDepartmentNews() {

    }
}
