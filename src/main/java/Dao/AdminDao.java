package Dao;

import Models.Admin;

import java.util.List;

public interface AdminDao {

    void add(Admin admin);

    Admin getById(int id);
    List <Admin> getAdmins();

    void deleteUser(int id);
}
