package Dao;

import Models.CompanyNews;

import java.util.List;

public interface CompanyNewsDao {
    void add(CompanyNews companyNews);

    CompanyNews getById(int id);

    List <CompanyNews> allCompanyNews();
    CompanyNews deleteById(int id);
    void deleteAllCompanyNews();
}
