package Models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Member {

    private int id;
    private String name;
    private String position;
    private int departmentId;
    private String[] roles;
    private String rolesString;

    public boolean isAdmin;

    public Member(String name, String position, String[] roles, int departmentId){
        this.name = name;
        this.position = position;
        this.departmentId = departmentId;
        this.roles = roles;
        this.rolesString = this.getRolesString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return departmentId == member.departmentId
                && Objects.equals(name, member.name)
                && Objects.equals(position, member.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, departmentId, roles, rolesString);
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

    public String getPosition() {
        return position;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRolesString() {
        this.rolesString = Arrays.asList(this.roles).stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public String getRolesString() {
        return Arrays.asList(this.roles).stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public int getDepartmentId() {
        return departmentId;
    }

}
