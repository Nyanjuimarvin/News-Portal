package Models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employee;

    public Employee setUp(){
        List <String> testRoles = new ArrayList<>();
        testRoles.add("Print Papers");
        testRoles.add("review code");
        testRoles.add("Troubleshoot");
        return new Employee("Gordon","salaryMan",testRoles,3);
    }

    @Test
    @DisplayName("Instantiates Correctly")
    public void Employee_InstantiatesCorrectly_True(){
        employee = setUp();
        assertTrue(employee instanceof Employee);
    }

    @Test
    @DisplayName("Instantiates With Properties")
    public void Employee_InstantiatesWithProperties(){
        List <String> roles = new ArrayList<>();
        roles.add("Print Papers");
        roles.add("review code");
        roles.add("Troubleshoot");

        employee = setUp();
        assertEquals("Gordon",employee.getName());
        assertEquals("salaryMan",employee.getPosition());
        assertEquals(roles,employee.getRoles());
    }


    @Test
    @DisplayName("Instantiates With Id")
    public void Employee_InstantiatesWithId(){
        employee = setUp();
        employee.setId(3);
        assertEquals(3,employee.getId());
    }

    @Test
    @DisplayName("Roles is returned as String")
    public void roles_IsReturnedAsString(){
        employee = setUp();
        List <String> roles1 = new ArrayList<>();
        roles1.add("Print Papers");
        roles1.add("review code");
        roles1.add("Troubleshoot");

        String role1String = roles1.stream().map(Objects::toString).collect(Collectors.joining(", "));
        assertEquals(role1String,employee.getRolesString());
    }

}