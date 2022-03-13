package Dao;

import Models.Member;
import org.sql2o.Sql2o;

import java.util.List;

public interface MemberDao {

    void add(Member member);

    Member getById(int id);
    List <Member> getAllMembers();

    void updateMember();

    void deleteById(int id);
    void deleteAll();
}
