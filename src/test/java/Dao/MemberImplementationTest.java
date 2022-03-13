package Dao;

import Models.Member;
import org.junit.jupiter.api.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberImplementationTest {

    Member member;

    private static Connection conn;
    private static MemberImplementation memberImplementation;


    @BeforeAll
    public static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/organization_news_test";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        memberImplementation = new MemberImplementation(sql2o);
        conn = sql2o.open();
    }


    @AfterEach
    public void tearDown(){
        memberImplementation.deleteAll();
    }

    @AfterAll
    public static void shutDown() {
        conn.close();
    }


    @Test
    @DisplayName("Adding Sets Id")
    public void add_SetsMemberId(){
        member = setUpMember();
        int initialId = member.getId();
        memberImplementation.add(member);

        assertNotEquals(initialId,member.getId());
    }

//    @Test
//    @DisplayName("Get member by Id")
//    public void getById_ReturnsMemberAtId() throws Exception{
//        member = setUpMember();
//        memberImplementation.add(member);
//        assertEquals(member,memberImplementation.getById(member.getId()));
//    }

    @Test
    @DisplayName("Get all returns all members")
    public void getAllMembers_ReturnsAllMembersInLIst(){
        member = setUpMember();

        List <String> roles = new ArrayList<>();
        roles.add("Listen to employee problems");
        roles.add("Keep work environment Safe");
        roles.add("Evaluate Perfomance");
        Member member1 = new Member("Valdamjong","HR Manager",roles,7);
        memberImplementation.add(member);
        memberImplementation.add(member1);

        assertEquals(2,memberImplementation.getAllMembers().size());

    }

    @Test
    @DisplayName("Delete all deletes members")
    public void deleteAll_DeletesAllMembers(){

        member = setUpMember();
        List <String> roles = new ArrayList<>();
        roles.add("Listen to employee problems");
        roles.add("Keep work environment Safe");
        roles.add("Evaluate Perfomance");
        Member member1 = new Member("Valdamjong","HR Manager",roles,7);
        memberImplementation.add(member);
        memberImplementation.add(member1);
        memberImplementation.deleteAll();
        assertEquals(0,memberImplementation.getAllMembers().size());
    }

    @Test
    @DisplayName("Delete by Id deletes member")
    public void deleteById_DeletesMember(){

        member = setUpMember();
        List <String> roles = new ArrayList<>();
        roles.add("Listen to employee problems");
        roles.add("Keep work environment Safe");
        roles.add("Evaluate Perfomance");
        Member member1 = new Member("Valdamjong","HR Manager",roles,7);
        memberImplementation.add(member);
        memberImplementation.add(member1);
        memberImplementation.deleteById(member1.getId());
        assertFalse(memberImplementation.getAllMembers().contains(member1));
    }

    public Member setUpMember(){
        List <String> roles = new ArrayList<>();
        roles.add("Pitch ideas");
        roles.add("Recruit business smart people");
        roles.add("Market goods worldwide");
        return new Member("Roggvir","Marketing Strategist",roles,3);
    }
}