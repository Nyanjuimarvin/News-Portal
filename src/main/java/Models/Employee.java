package Models;

import java.util.Objects;

public class Employee {

    private int id;
    private String name;
    private String position;
    private int departmentId;

    public Employee(String name, String position, int departmentId){
        this.name = name;
        this.position = position;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return departmentId == employee.departmentId
                && Objects.equals(name, employee.name)
                && Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, departmentId);
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getDepartmentId() {
        return departmentId;
    }
}
