package seedu.smartlib.model.record;

/**
 * The IsReturned class indicates whether the book associated with a record has been returned to SmartLib.
 */
public class IsReturned {

    private final String value;

    /**
     * Constructs an {@code DateBorrowed}.
     *
     * @param isReturned A boolean indicating whether a book is returned.
     */
    public IsReturned(boolean isReturned) {
        value = isReturned + "";
    }

    /**
     * Returns true if a given string is a valid returned object.
     *
     * @param test string to be tested
     * @return true if a given string is a valid returned object, and false otherwise.
     */
    public static boolean isValidIsReturned(String test) {
        return test.equals("true") || test.equals("false");
    }

    /**
     * Returns this IsReturned object in String format.
     *
     * @return this IsReturned object in String format.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if this IsReturned object is equal to another IsReturned object.
     *
     * @param other the other IsReturned object to be compared.
     * @return true if this IsReturned object is equal to the other IsReturned object, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsReturned // instanceof handles nulls
                && value.equals(((IsReturned) other).value)); // state check
    }

    /**
     * Generates a hashcode for this IsReturned object.
     *
     * @return the hashcode for this IsReturned object.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
