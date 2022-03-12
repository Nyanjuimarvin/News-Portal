package Models;

import java.util.Objects;

public class News {
    private int id;
    private int employeeId;
    private String information;

    public String type;

    public News(int employeeId, String information) {
        this.employeeId = employeeId;
        this.information = information;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getInformation() {
        return information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return employeeId == news.employeeId
                && Objects.equals(information, news.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, information);
    }

}
