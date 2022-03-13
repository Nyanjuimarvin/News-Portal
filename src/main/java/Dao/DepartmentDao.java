package Dao;

import Models.Department;
import Models.DepartmentNews;
import Models.Member;

import java.util.List;

public interface DepartmentDao {

    void add(Department department);

    Department getById(int id);
    List<Department> getAllDepartments();

    //Get all members in department & all news in a department
    List <Member> getAllMembersInDepartment(int id);
    List <DepartmentNews> getDepartmentNews(int id);

    //Implement Later
    void updateDepartment();

    void deleteById(int id);
    void deleteAll();
    
}
