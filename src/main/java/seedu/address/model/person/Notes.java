package seedu.address.model.person;

import seedu.address.model.tag.Filterable;

public class Notes implements Filterable {

    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    private final boolean isEmpty;

    /**
     * Constructs an {@code Notes}.
     *
     * @param notes A valid note.
     */
    public Notes(String notes) {
        if (notes == null || notes.equals("")) {
            this.isEmpty = true;
            this.value = "";
        } else {
            this.isEmpty = false;
            this.value = notes;
        }

    }

    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean equals(Object other) {
        return other == this
                || (other instanceof Notes
                && value.equals(((Notes) other).value)
                && isEmpty == ((Notes) other).isEmpty);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean filter(String s) {
        return value.contains(s);
    }
}

