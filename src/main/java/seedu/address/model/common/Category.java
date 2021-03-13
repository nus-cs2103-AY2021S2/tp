package seedu.address.model.common;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Category {

    public static final String MESSAGE_CONSTRAINTS = "Category names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private String categoryName;

    /**
     * Constructs a {@code Category}.
     *
     * @param categoryName A valid category name.
     */
    public Category(String categoryName) {
        requireNonNull(categoryName);
        checkArgument(isValidCategoryName(categoryName), MESSAGE_CONSTRAINTS);
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
