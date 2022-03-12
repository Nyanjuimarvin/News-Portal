package Models;

import java.util.Objects;

public class News {
    private int id;
    private String information;
    private String category;

    public String type;

    public News(int employeeId, String information, String category) {
        this.information = information;
        this.category = category;
    }

    public String getInformation() {
        return information;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return Objects.equals(information, news.information)
                && Objects.equals(category, news.category)
                && Objects.equals(type, news.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash( information, category, type);
    }
}
