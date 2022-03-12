package Models;


import java.util.Objects;

public class Department {

    private int id;
    private String name;
    private String description;
    private int noOfEmployees;

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return noOfEmployees == that.noOfEmployees
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, noOfEmployees);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
