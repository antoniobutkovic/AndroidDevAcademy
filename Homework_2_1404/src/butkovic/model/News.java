package butkovic.model;

import java.util.List;

public class News {

    private String name;
    private String date;
    private String text;
    private Author author;
    private List<Category> categories;

    public News() {
    }

    public News(String name, String date, String text, Author author, List<Category> categories) {
        this.name = name;
        this.date = date;
        this.text = text;
        this.author = author;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public Author getAuthor() {
        return author;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
