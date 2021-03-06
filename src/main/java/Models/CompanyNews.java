package Models;

public class CompanyNews extends News implements Comparable <CompanyNews>{

    public static final String NEWS_TYPE = "company";

    public CompanyNews(String information, String category) {
        super(information, category);
        type = NEWS_TYPE;
    }


    @Override
    public int compareTo(CompanyNews o) {
        return Long.compare(this.getDateCreated(), o.getDateCreated());
    }
}
