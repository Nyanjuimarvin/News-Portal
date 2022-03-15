package Dao;

import Models.Department;
import Models.DepartmentNews;
import Models.Member;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentImplementationTest {
    Department department;

    private static Connection conn;
    private static MemberImplementation memberImplementation;
    private static DepartmentImplementation departmentImplementation;
    private static DepartmentNewsImplementation departmentNewsImplementation;

    @BeforeAll
    public static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/organization_news_test";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        departmentImplementation = new DepartmentImplementation(sql2o);
        departmentNewsImplementation = new DepartmentNewsImplementation(sql2o);
        memberImplementation = new MemberImplementation(sql2o);
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
    @DisplayName("Returns Members")
    public void getAllMembersInDepartment_ReturnsAllMembersInDepartment() {
        department = setUpDepartment();
        departmentImplementation.add(department);
        String [] roles = {"Jester","Keeper of coffins"};
        Member member = new Member("Cicero","Assasin", roles,department.getId());
        Member member1 = new Member("Veezera","member",roles,department.getId());
        memberImplementation.add(member);
        memberImplementation.add(member1);
        assertTrue(departmentImplementation.getAllMembersInDepartment(department.getId()).contains(memberImplementation.getById(member.getId())));
        assertTrue(departmentImplementation.getAllMembersInDepartment(department.getId()).contains(memberImplementation.getById(member1.getId())));
    }

    @Test
    @DisplayName("News for a given department")
    public void getDepartmentNews_ReturnsDepartmentNews() {
        department = setUpDepartment();
        departmentImplementation.add(department);
        DepartmentNews depNews1 = new DepartmentNews("The Medicine rollout","Feedback");
        DepartmentNews depNews2 = new DepartmentNews("The FoxDie virus is getting out of hand","warning");
        departmentNewsImplementation.add(depNews1);
        departmentNewsImplementation.add(depNews2);
        departmentNewsImplementation.addDepartmentNews(department,depNews1);
        departmentNewsImplementation.addDepartmentNews(department,depNews2);
        List <DepartmentNews> testNews = new ArrayList<>();
        testNews.add(depNews1);
        testNews.add(depNews2);

        assertEquals(testNews,departmentImplementation.getDepartmentNews(department.getId()));
    }

    //Coming back
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