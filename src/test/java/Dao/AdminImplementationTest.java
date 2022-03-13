package Dao;

import Models.Admin;
import Models.Member;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AdminImplementationTest {

    Admin admin;

    private static Connection conn;
    private static AdminImplementation adminImplementation;
    private static MemberImplementation memberImplementation;

    @BeforeAll
    public static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/organization_news_test";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        adminImplementation = new AdminImplementation(sql2o);
        memberImplementation = new MemberImplementation(sql2o);
        conn = sql2o.open();
    }

    @AfterAll
    public static void shutDown(){
        conn.close();
    }

    @Test
    @DisplayName("Add Sets Id")
    public void add() {
        admin = setUpAdmin();
        int initialId = admin.getId();
        adminImplementation.add(admin);
        assertNotEquals(initialId,admin.getId());
    }

    @Test
    void getById() {
    }

    @Test
    void getAdmins() {
    }

    @Test
    @DisplayName("Deletes Members")
    public void deleteUser_DeletesUsers() {
        admin = setUpAdmin();
        Member member = setUpMember();
        adminImplementation.add(admin);
        memberImplementation.add(member);
        adminImplementation.deleteUser(member.getId());
        assertFalse(memberImplementation.getAllMembers().contains(member));
    }

    public Admin setUpAdmin(){
        return new Admin("Jarl Balgruuf");
    }

    public Member setUpMember(){
        String[] roles = {"Recruit pirates","Cut down rejects","Preach about insanity"};
        return new Member("Vaas","Captain", Arrays.asList(roles),4);
    }
}