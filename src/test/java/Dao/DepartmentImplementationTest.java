package Dao;

import Models.Department;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentImplementationTest {
    Department department;

    private static Connection conn;
    private static DepartmentImplementation departmentImplementation;

    @BeforeAll
    public static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/organization_news_test";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        departmentImplementation = new DepartmentImplementation(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    public void tearDown(){
        departmentImplementation.deleteAll();
    }

    @AfterAll
    public static void shutDown() {
        conn.close();
    }


    @Test
    @DisplayName("Add sets Id")
    public void add_SetsId() {
        department = setUpDepartment();
        int initialId = department.getId();
        departmentImplementation.add(department);
        assertNotEquals(initialId,department.getId());
    }

    @Test
    @DisplayName("Returns department")
    public void getById_ReturnsDepartmentAtId() {
        department = setUpDepartment();
        departmentImplementation.add(department);
        assertEquals(department, departmentImplementation.getById(department.getId()));
    }

    @Test
    @DisplayName("Get all Departments")
    public void getAllDepartments_ReturnsAllDepartments() {
        department = setUpDepartment();
        Department department1 = new Department("On Field","Get on the ground details");
        departmentImplementation.add(department);
        departmentImplementation.add(department1);
        assertEquals(2, departmentImplementation.getAllDepartments().size());
    }

    @Test
    void getAllMembersInDepartment() {
    }

    @Test
    @DisplayName("News for a given department")
    public void getDepartmentNews_ReturnsDepartmentNews() {
    }

    @Test
    void updateDepartment() {
    }

    @Test
    @DisplayName("Delete department at id")
    public void deleteById_DeletesGivenDepartment() {
        department = setUpDepartment();
        Department department1 = new Department("On Field","Get on the ground details");
        departmentImplementation.add(department);
        departmentImplementation.add(department1);
        departmentImplementation.deleteById(department1.getId());
        assertFalse(departmentImplementation.getAllDepartments().contains(department1));
    }

    @Test
    @DisplayName("Delete Departments")
    public void deleteAll_DeletesAllDepartments() {
        department = setUpDepartment();
        Department department1 = new Department("On Field","Get on the ground details");
        departmentImplementation.add(department);
        departmentImplementation.add(department1);
        departmentImplementation.deleteAll();
        assertEquals(0,departmentImplementation.getAllDepartments().size());
    }


    public Department setUpDepartment(){
        return new Department("Science","Conduct genetic research");
    }
}