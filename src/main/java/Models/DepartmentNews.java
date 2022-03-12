package Models;

public class DepartmentNews extends News implements Comparable<DepartmentNews>{

    public DepartmentNews(int employeeId, String information) {
        super(employeeId, information);
    }

    @Override
    public int compareTo(DepartmentNews o) {
        return 0;
    }
}
