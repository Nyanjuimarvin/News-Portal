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
    @DisplayName("Adds news to department")
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
    @DisplayName("Get DepartmentNews At Id")
    public void getById_ReturnsDepartmentNews() {
        departmentNews = setUpDepartmentNews();
        DepartmentNews departmentNews1 = new DepartmentNews("Drop gear at given Co-ords","Informative");
        departmentNewsImplementation.add(departmentNews);
        departmentNewsImplementation.add(departmentNews1);
        assertEquals(departmentNews1, departmentNewsImplementation.getById(departmentNews1.getId()));
    }

    @Test
    @DisplayName("Contains All")
    public void getAll_ContainsAllDepartmentNews() {
        departmentNews = setUpDepartmentNews();
        DepartmentNews departmentNews1 = new DepartmentNews("Drop gear at given Co-ords","Informative");
        departmentNewsImplementation.add(departmentNews);
        departmentNewsImplementation.add(departmentNews1);
        assertTrue(departmentNewsImplementation.getAll().contains(departmentNews));
        assertTrue(departmentNewsImplementation.getAll().contains(departmentNews1));
    }


    @Test
    @DisplayName("Deletes department news")
    public void deleteById_DeletesDepartmentNewsAtId() {
        departmentNews = setUpDepartmentNews();
        DepartmentNews departmentNews1 = new DepartmentNews("Drop gear at given Co-ords","Informative");
        departmentNewsImplementation.add(departmentNews);
        departmentNewsImplementation.add(departmentNews1);
        departmentNewsImplementation.deleteById(departmentNews1.getId());
        assertFalse(departmentNewsImplementation.getAll().contains(departmentNews1));
    }

    @Test
    @DisplayName("Deletes All Department News")
    public void deleteDepartmentNews_DeletesAllDepartmentNews() {
        departmentNews = setUpDepartmentNews();
        DepartmentNews departmentNews1 = new DepartmentNews("Drop gear at given Co-ords","Informative");
        departmentNewsImplementation.add(departmentNews);
        departmentNewsImplementation.add(departmentNews1);
        departmentNewsImplementation.deleteDepartmentNews();
        assertEquals(0,departmentNewsImplementation.getAll().size());
    }

    public DepartmentNews setUpDepartmentNews(){
        return new DepartmentNews("Deploy Freddy Fazbear","Order");
    }

    public Department setUpDepartment(){
        return new Department("Chopper","Deploy to risk areas");
    }
}