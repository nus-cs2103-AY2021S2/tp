package seedu.smartlib.model.book;

/**
 * Represents a publisher in SmartLib.
 */
public class Publisher {

    private final String fullName;

    /**
     * Constructs an {@code Publisher}.
     *
     * @param fullName A valid full name of the publisher.
     */
    public Publisher(String fullName) {
        this.fullName = fullName;
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

