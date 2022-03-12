package Models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentsTest {

    Department department;

    public Department setUpDepartment(){
        return new Department("R & D", "Research and Development");
    }

    @Test
    @DisplayName("Instantiates Correctly")
    public void Department_InstantiatesCorrectly_True(){
        department = setUpDepartment();
        assertTrue(department instanceof Department);
    }

    @Test
    @DisplayName("Instantiates With Properties")
    public void Department_InstantiatesWithProperties(){
        department = setUpDepartment();
        assertEquals("R & D", department.getName());
        assertEquals("Research and Development",department.getDescription());
    }


    @Test
    @DisplayName("Instantiates With Id")
    public void Department_InstantiatesWithId(){
        department =setUpDepartment();
        department.setId(1);
        assertEquals(1, department.getId());
    }

}