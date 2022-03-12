package Models;

public class CompanyNews extends News implements Comparable<CompanyNews>{

    public CompanyNews(int employeeId,String information){
        super(employeeId, information);
    }


    @Override
    public int compareTo(CompanyNews o) {
        return 0;
    }
}
