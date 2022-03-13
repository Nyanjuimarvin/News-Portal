package Models;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Member {

    private int id;
    private String name;
    private String position;
    private int departmentId;
    private List <String> roles;
    private String rolesString;

    public boolean isAdmin;

    public Member(String name, String position, List <String> roles, int departmentId){
        this.name = name;
        this.position = position;
        this.departmentId = departmentId;
        this.roles = roles;
        this.rolesString = roles.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return departmentId == member.departmentId
                && Objects.equals(name, member.name)
                && Objects.equals(position, member.position)
                && Objects.equals(roles, member.roles)
                && Objects.equals(rolesString, member.rolesString);
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

    public List<String> getRoles() {
        return roles;
    }

    public String getRolesString() {
        return rolesString;
    }

    public int getDepartmentId() {
        return departmentId;
    }
}
