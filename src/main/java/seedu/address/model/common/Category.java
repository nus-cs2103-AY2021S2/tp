package seedu.address.model.common;

public class Category {
    public String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String toString() {
        return this.categoryName;
    }

}
