package seedu.address.model.common;

public class Category {
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String toString() {
        return this.categoryName;
    }

}
