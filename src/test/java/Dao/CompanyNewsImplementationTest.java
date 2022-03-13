package Dao;

import Models.CompanyNews;
import Models.DepartmentNews;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class CompanyNewsImplementationTest {

    CompanyNews companyNews;

    private static Connection conn;
    private static CompanyNewsImplementation companyNewsImplementation;

    @BeforeAll
    public static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/organization_news_test";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        companyNewsImplementation = new CompanyNewsImplementation(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    public void tearDown(){
        companyNewsImplementation.deleteAllCompanyNews();
    }

    @AfterAll
    public static void shutDown(){
        conn.close();
    }

    @Test
    @DisplayName("Add Sets Id")
    public void add_SetsId() {
        companyNews = setUpCompanyNews();
        int initialId = companyNews.getId();
        companyNewsImplementation.add(companyNews);
        assertNotEquals(initialId,companyNews.getId());
    }

    @Test
    @DisplayName("Get CompanyNews At Id")
    public void getById_ReturnsCompanyNewsAtId() {
        companyNews = setUpCompanyNews();
        CompanyNews companyNews1 = new CompanyNews("Drop gear at given Co-ords","Informative");
        companyNewsImplementation.add(companyNews);
        companyNewsImplementation.add(companyNews1);
        assertEquals(companyNews1, companyNewsImplementation.getById(companyNews1.getId()));
    }

    @Test
    @DisplayName("Contains All Company News")
    public void allCompanyNews_ContainsAll() {
        companyNews = setUpCompanyNews();
        CompanyNews companyNews1 = new CompanyNews("Drop gear at given Co-ords","Informative");
        companyNewsImplementation.add(companyNews);
        companyNewsImplementation.add(companyNews1);
        assertTrue(companyNewsImplementation.allCompanyNews().contains(companyNews1));
        assertTrue(companyNewsImplementation.allCompanyNews().contains(companyNews1));
    }

    @Test
    @DisplayName("Deletes company news")
    public void deleteById_DeletesNewsAtId() {
        companyNews = setUpCompanyNews();
        CompanyNews companyNews1 = new CompanyNews("Drop gear at given Co-ords","Informative");
        companyNewsImplementation.add(companyNews);
        companyNewsImplementation.add(companyNews1);
        companyNewsImplementation.deleteById(companyNews1.getId());
        assertFalse(companyNewsImplementation.allCompanyNews().contains(companyNews1));
    }

    @Test
    @DisplayName("Deletes All Company News")
    public void deleteAllCompanyNews_deletesCompanyNews() {
        companyNews = setUpCompanyNews();
        CompanyNews companyNews1 = new CompanyNews("Drop gear at given Co-ords","Informative");
        companyNewsImplementation.add(companyNews);
        companyNewsImplementation.add(companyNews);
        companyNewsImplementation.deleteAllCompanyNews();
        assertEquals(0,companyNewsImplementation.allCompanyNews().size());
    }

    public CompanyNews setUpCompanyNews() {
        return new CompanyNews("The La Li Lu Le Lo","Intel");
    }
}