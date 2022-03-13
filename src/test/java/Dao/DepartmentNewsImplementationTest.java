package Dao;

import Models.Department;
import Models.DepartmentNews;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentNewsImplementationTest {
    DepartmentNews departmentNews;
    Department department;

    private static Connection conn;
    private static DepartmentImplementation departmentImplementation;
    private static DepartmentNewsImplementation departmentNewsImplementation;

    @BeforeAll
    public static void setUp(){
        String connectionString = "jdbc:postgresql://localhost:5432/organization_news_test";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        departmentNewsImplementation = new DepartmentNewsImplementation(sql2o);
        departmentImplementation = new DepartmentImplementation(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    public void tearDown(){
        departmentNewsImplementation.deleteDepartmentNews();
    }

    @AfterAll
    public static void shutDown(){
        conn.close();
    }

    @Test
    @DisplayName("Add Sets Id")
    void add_SetsId() {
        departmentNews = setUpDepartmentNews();
        int initialId = departmentNews.getId();
        departmentNewsImplementation.add(departmentNews);

        assertNotEquals(initialId,departmentNews.getId());
    }

    @Test
    void addDepartmentNews_ReturnsNews() {
        departmentNews = setUpDepartmentNews();
        department = setUpDepartment();
        departmentNewsImplementation.add(departmentNews);
        departmentImplementation.add(department);

        DepartmentNews departmentNews1 = new DepartmentNews("Drop gear at given Co-ords","Informative");
        departmentNewsImplementation.add(departmentNews1);
        departmentNewsImplementation.addDepartmentNews(department,departmentNews);
        departmentNewsImplementation.addDepartmentNews(department,departmentNews1);
        List <DepartmentNews> testNews = new ArrayList<>();
        testNews.add(departmentNews);
        testNews.add(departmentNews1);

        assertEquals(testNews,departmentImplementation.getDepartmentNews(department.getId()));
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void allCompanyNews() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteDepartmentNews() {
    }

    public DepartmentNews setUpDepartmentNews(){
        return new DepartmentNews("Deploy Freddy Fazbear","Order");
    }

    public Department setUpDepartment(){
        return new Department("Chopper","Deploy to risk areas");
    }
}