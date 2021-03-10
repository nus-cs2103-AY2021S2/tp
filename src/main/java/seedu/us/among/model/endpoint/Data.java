package seedu.us.among.model.endpoint;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.util.AppUtil.checkArgument;

/**
 * Represents a Endpoint's data in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidData(String)}
 */
public class Data {
    //to-do Jun Xiong Tan Jin force data to take in only json files
    public static final String MESSAGE_CONSTRAINTS = "Data must be in a {\"key\": \"value\"} format.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;
    public final boolean isEmpty;

    /**
     * Constructs an {@code Data}.
     *
     * @param data A valid Data.
     */
    public Data(String data) {
        requireNonNull(data);
        checkArgument(isValidData(data), MESSAGE_CONSTRAINTS);
        value = data;
        isEmpty = false;
    }

    /**
     * Constructs an empty {@code Data}
     */
    public Data() {
        value = "";
        isEmpty = true;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Returns true if a given string is a valid Data.
     */
    public static boolean isValidData(String test) {
        String headerString = test.strip().toString();
        String[] headerPair = headerString.split(":", 2);
        //Checks if there is a ":" in the data entered.
        if (headerPair.length != 2) { 
            return false;
        }
        //Checks if header is enclosed with { }
        if (headerString.startsWith("{") && headerString.endsWith("}")) {
            return test.matches(VALIDATION_REGEX);
        } else { 
            return false;
        }
    }

    public static boolean isEmptyData(String test) {
        return test.equals("");
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Data // instanceof handles nulls
                && value.equals(((Data) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
