package Models;

import java.util.List;
import java.util.Objects;

public class Departments {

    private int id;
    private String name;
    private String description;
    private int employeeId;
    private int noOfEmployees;
    private int departmentSize;
    private List <String> addEmployee;


    public Departments(String name, String description,int employeeId, int noOfEmployees, int departmentSize) {
        this.name = name;
        this.description = description;
        this.noOfEmployees = noOfEmployees;
        this.employeeId = employeeId;
        this.departmentSize = departmentSize;
        this.addEmployee.add("Added");
        this.departmentSize = addEmployee.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departments)) return false;
        Departments that = (Departments) o;
        return noOfEmployees == that.noOfEmployees
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, noOfEmployees);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }
}
