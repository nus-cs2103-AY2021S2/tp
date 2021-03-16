package seedu.smartlib.model.book;

import seedu.smartlib.commons.core.name.Name;

/**
 * Represents a publisher in SmartLib.
 */
public class Publisher {

    public static final String MESSAGE_CONSTRAINTS =
            "Publisher's names should only contain alphanumeric characters and spaces, and it should not be blank";

    private final Name fullName;

    /**
     * Constructs an {@code Publisher}.
     *
     * @param fullName A valid full name of the publisher.
     */
    public Publisher(Name fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns true if a given string is a valid publisher's name.
     */
    public static boolean isValidPublisher(String test) {
        return Name.isValidName(test);
    }

    @Override
    public String toString() {
        return fullName.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Publisher // instanceof handles nulls
                && fullName.equals(((Publisher) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}

