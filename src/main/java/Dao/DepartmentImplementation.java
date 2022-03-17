package Dao;

import Models.Department;
import Models.DepartmentNews;
import Models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class DepartmentImplementation implements DepartmentDao{

    private final Sql2o sql2o;
    public DepartmentImplementation(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department) {

        String sql = "INSERT INTO departments(name,description) VALUES (:name,:description)";

        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                               .bind(department)
                               .executeUpdate()
                               .getKey();
            department.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Department getById(int id) {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM departments WHERE id = :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public List<Member> getAllMembersInDepartment(int id) {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT id,name,position,rolesstring,departmentid FROM members WHERE departmentid = :id")
                    .addParameter("id",id)
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public List <DepartmentNews> getDepartmentNews(int id) {

        List <DepartmentNews> departmentNews = new ArrayList<>();

        try(Connection conn = sql2o.open()){
            List <Integer> keys =  conn.createQuery("SELECT newsid FROM department_news WHERE departmentid = :id")
                    .addParameter("id",id)
                    .executeAndFetch(Integer.class);
            for (int key: keys){
                departmentNews.add(
                        conn.createQuery("SELECT * FROM news WHERE id = :id")
                                .addParameter("id",key)
                                .executeAndFetchFirst(DepartmentNews.class)
                );
            }
            return departmentNews;
        }
    }

    //Remember me
    @Override
    public void updateDepartment() {

    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM departments WHERE id = :id")
                    .addParameter("id",id).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try(Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM departments *").executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }
}
