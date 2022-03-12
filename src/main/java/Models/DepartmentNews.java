package Models;

public class DepartmentNews extends News implements Comparable <DepartmentNews>{

    public static final String NEWS_TYPE = "department";

    public DepartmentNews(int employeeId, String information, String category) {
        super(employeeId, information, category);
        type = NEWS_TYPE;
    }


    @Override
    public int compareTo(DepartmentNews o) {
        return 0;
    }
}
