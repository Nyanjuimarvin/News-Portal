package Dao;

import Models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class MemberImplementation implements MemberDao{

    private final Sql2o sql2o;
    public MemberImplementation(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Member member) {
        String sql = "INSERT INTO members(name,position,roles,departmentid) VALUES (:name,:position,:role,:departmentId)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .addParameter("role",member.getRolesString())
                               .bind(member)
                               .executeUpdate()
                               .getKey();
            member.setId(id);
        } catch (Sql2oException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @Override
    public Member getById(int id) {

        try(Connection conn = sql2o.open()){

            return conn.createQuery("SELECT * FROM members WHERE id = :id")
                       .addParameter("id",id)
                       .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public List<Member> getAllMembers() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM members")
                       .executeAndFetch(Member.class);
        }
    }

    @Override
    public void updateMember() {

    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM members WHERE id = :id")
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try (Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM members *").executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }
}
