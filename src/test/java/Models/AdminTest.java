package Models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    public Admin setUpAdmin(){
        Admin admin = new Admin("Novik");
        admin.setId(1);
        return admin;
    }

    @Test
    @DisplayName("Instantiates Correctly")
    public void Admin_InstantiatesCorrectly_True(){
        assertTrue(setUpAdmin() instanceof Admin);
    }

    @Test
    @DisplayName("Instantiates With Properties")
    public void Admin_InstantiatesWithProperties(){
        assertEquals(1,setUpAdmin().getId());
        assertEquals("Novik",setUpAdmin().getName());
    }
}