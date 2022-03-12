package Models;

import java.util.Objects;

public class Admin {

    private int id;
    private String name;
    private String position;
    private int employeeId;

    public Admin(String name, String position, int employeeId) {
        this.name = name;
        this.position = position;
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return employeeId == admin.employeeId
                && Objects.equals(name, admin.name)
                && Objects.equals(position, admin.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, employeeId);
    }
}
