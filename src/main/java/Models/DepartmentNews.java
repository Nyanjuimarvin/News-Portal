package Models;

public class DepartmentNews extends News implements Comparable <DepartmentNews>{

    public static final String NEWS_TYPE = "department";

    public DepartmentNews(int employeeId, String information) {
        super(employeeId, information);
        type = NEWS_TYPE;
    }

    @Override
    public int compareTo(DepartmentNews o) {
        return 0;
    }
}
