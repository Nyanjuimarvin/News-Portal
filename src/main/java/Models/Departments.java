package Models;

import java.util.Objects;

public class Departments {

    private int id;
    private String name;
    private String description;
    private int noOfEmployees;


    public Departments(String name, String description, int noOfEmployees) {
        this.name = name;
        this.description = description;
        this.noOfEmployees = noOfEmployees;
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
