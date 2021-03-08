package seedu.address.model.common;

public class Category {

    public static final String MESSAGE_CONSTRAINTS = "Category names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidCategoryName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String toString() {
        return this.categoryName;
    }

}
