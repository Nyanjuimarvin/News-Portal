package Dao;



import Models.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {

    void add();

    DepartmentNews getById(int id);

    List<DepartmentNews> allCompanyNews();
    DepartmentNews deleteById(int id);
    void deleteDepartmentNews();
}
