package Models;

import jdk.internal.foreign.abi.aarch64.linux.LinuxAArch64Linker;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Employee {

    private int id;
    private String name;
    private String position;
    private int departmentId;
    private List <String> roles;
    private String rolesString;

    public Employee(String name, String position,List <String> roles, int departmentId){
        this.name = name;
        this.position = position;
        this.departmentId = departmentId;
        this.roles = roles;
        this.rolesString = roles.stream()
                .map(Object::toString).
                collect(Collectors.joining());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return departmentId == employee.departmentId
                && Objects.equals(name, employee.name)
                && Objects.equals(position, employee.position)
                && Objects.equals(roles, employee.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, departmentId, roles);
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
