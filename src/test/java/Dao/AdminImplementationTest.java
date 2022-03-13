package Dao;

import Models.Admin;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class AdminImplementationTest {

    private static Connection conn;
    private static AdminImplementation adminImplementation;

    @BeforeAll
    public static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/organization_news_test";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        adminImplementation = new AdminImplementation(sql2o);
        conn = sql2o.open();
    }

    @AfterAll
    public static void shutDown(){
        conn.close();
    }

    @Test
    @DisplayName("Add Sets Id")
    void add() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAdmins() {
    }

    @Test
    void deleteUser() {
    }

}