package Models;

public class CompanyNews extends News implements Comparable <CompanyNews>{

    public static final String NEWS_TYPE = "company";

    public CompanyNews(int employeeId, String information, String category) {
        super(employeeId, information, category);
        type = NEWS_TYPE;
    }


    @Override
    public int compareTo(CompanyNews o) {
        return 0;
    }
}
