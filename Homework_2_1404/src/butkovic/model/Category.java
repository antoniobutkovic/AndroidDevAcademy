package butkovic.model;

public class Category {

    private String name;
    private String shortDesc;

    public Category() {
    }

    public Category(String name, String shortDesc) {
        this.name = name;
        this.shortDesc = shortDesc;
    }

    public String getName() {
        return name;
    }

    public String getShortDesc() {
        return shortDesc;
    }
}
