package Models;

public class CompanyNews extends News implements Comparable <CompanyNews>{

    public static final String NEWS_TYPE = "company";
    public CompanyNews(int employeeId,String information){
        super(employeeId, information);
        type = NEWS_TYPE;
    }


    @Override
    public int compareTo(CompanyNews o) {
        return 0;
    }
}
