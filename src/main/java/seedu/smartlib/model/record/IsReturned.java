package seedu.smartlib.model.record;

public class IsReturned {

    public final String value;


    /**
     * Constructs an {@code DateBorrowed}.
     *
     * @param isReturned A boolean.
     */
    public IsReturned(boolean isReturned) {
        value = isReturned + "";
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidIsReturned(String test) {
        return test.equals("true") || test.equals("false");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsReturned // instanceof handles nulls
                && value.equals(((IsReturned) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
