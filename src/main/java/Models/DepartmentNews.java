package Models;

public class DepartmentNews extends News implements Comparable <DepartmentNews>{

    public static final String NEWS_TYPE = "department";

    public DepartmentNews( String information, String category) {
        super(information, category);
        type = NEWS_TYPE;
    }


    @Override
    public int compareTo(DepartmentNews o) {
        return Long.compare(this.getDateCreated(), o.getDateCreated());
    }
}
