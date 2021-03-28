package seedu.address.model.grade;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tag.Filterable;

public class GradedItem implements Filterable {
    public static final String MESSAGE_CONSTRAINTS =
            "Graded item should only contain alphanumeric characters and spaces, "
                    + "and it should not be blank";

    /*
     * The first character of the graded item must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String gradedItem;

    /**
     * Constructs a {@code Name}.
     *
     * @param gradedItem A valid gradedItem.
     */
    public GradedItem(String gradedItem) {
        requireNonNull(gradedItem);
        checkArgument(isValidGradedItem(gradedItem), MESSAGE_CONSTRAINTS);
        this.gradedItem = gradedItem;
    }

    public String getItem() {
        return this.gradedItem;
    }

    /**
     * Returns true if a given string is a valid graded item.
     */
    public static boolean isValidGradedItem(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return gradedItem;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradedItem // instanceof handles nulls
                && gradedItem.equals(((GradedItem) other).gradedItem)); // state check
    }

    @Override
    public int hashCode() {
        return gradedItem.hashCode();
    }

    @Override
    public boolean filter(String s) {
        return this.gradedItem.contains(s);
    }
}
