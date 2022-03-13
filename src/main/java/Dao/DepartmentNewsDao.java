package Dao;



import Models.Department;
import Models.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {

    void add(DepartmentNews departmentNews);
    void addDepartmentNews(Department department, DepartmentNews departmentNews);

    DepartmentNews getById(int id);
    DepartmentNews getAll();

    List<DepartmentNews> allCompanyNews();
    DepartmentNews deleteById(int id);
    void deleteDepartmentNews();
}
