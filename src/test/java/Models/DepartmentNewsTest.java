package Models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentNewsTest {

    DepartmentNews departmentNews;

    public DepartmentNews setUpDNews(){
        return new DepartmentNews("The sales team delivered great results","Motivation");
    }


    @Test
    @DisplayName("Instantiates Correctly")
    public void DepartmentNews_InstantiatesCorrectly_True(){
        departmentNews = setUpDNews();
        assertTrue(departmentNews instanceof DepartmentNews);
    }

    @Test
    @DisplayName("Instantiates With Properties")
    public void DepartmentNews_InstantiatesWithProperties_True(){
        departmentNews = setUpDNews();

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat humanDate = new SimpleDateFormat("MMMM dd YYYY 'at' hh:mm aaa");
        String testString = humanDate.format(date);

        assertEquals("The sales team delivered great results",departmentNews.getInformation());
        assertEquals("Motivation",departmentNews.getCategory());
        assertEquals(testString,departmentNews.getReadableDate());
    }

    @Test
    @DisplayName("Instantiates With Id")
    public void DepartmentNews_InstantiatesWithId(){
        departmentNews = setUpDNews();
        departmentNews.setId(3);
        assertEquals(3,departmentNews.getId());
    }
}