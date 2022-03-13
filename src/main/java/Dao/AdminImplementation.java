package Dao;

import Models.Admin;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class AdminImplementation implements AdminDao{

    private final Sql2o sql2o;
    public AdminImplementation(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Admin admin) {
        String sql = "INSERT INTO admins (name) VALUES (:name)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(admin)
                    .executeUpdate()
                    .getKey();
            admin.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Admin getById(int id) {
        return null;
    }

    @Override
    public List<Admin> getAdmins() {
        return null;
    }

    @Override
    public void deleteUser(int id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM members WHERE id = :id")
                    .addParameter("id",id).executeUpdate();
        }
    }
}
