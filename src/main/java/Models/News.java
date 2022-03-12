package Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class News {
    private int id;
    private String information;
    private String category;
    private long dateCreated;
    private String readableDate;

    public String type;

    public News(String information, String category) {
        this.information = information;
        this.category = category;
        this.dateCreated = System.currentTimeMillis();
        this.setReadableDate();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDateCreated() {
        this.dateCreated = System.currentTimeMillis();
    }

    public void setReadableDate() {
        Date date = new Date(this.dateCreated);
        SimpleDateFormat humanDate = new SimpleDateFormat("MMMM dd YYYY 'at' hh:mm aaa");
        this.readableDate = humanDate.format(date);
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public String getReadableDate() {
        return readableDate;
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
