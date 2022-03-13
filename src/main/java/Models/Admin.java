package Models;

import java.util.Objects;

public class Admin {

    private int id;
    private String name;

    public Admin( String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(name, admin.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
