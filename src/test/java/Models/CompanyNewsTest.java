package Models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CompanyNewsTest {

    CompanyNews companyNews;

    public CompanyNews setUpDNews(){
        return new CompanyNews("The sales team delivered great results","Motivation");
    }


    @Test
    @DisplayName("Instantiates Correctly")
    public void CompanyNews_InstantiatesCorrectly_True(){
        companyNews = setUpDNews();
        assertTrue(companyNews instanceof CompanyNews);
    }

    @Test
    @DisplayName("Instantiates With Properties")
    public void CompanyNews_InstantiatesWithProperties_True(){
        companyNews = setUpDNews();

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat humanDate = new SimpleDateFormat("MMMM dd YYYY 'at' hh:mm aaa");
        String testString = humanDate.format(date);

        assertEquals("The sales team delivered great results",companyNews.getInformation());
        assertEquals("Motivation",companyNews.getCategory());
        assertEquals(testString,companyNews.getReadableDate());
    }

    @Test
    @DisplayName("Instantiates With Id")
    public void CompanyNews_InstantiatesWithId(){
        companyNews = setUpDNews();
        companyNews.setId(3);
        assertEquals(3,companyNews.getId());
    }

}