package seedu.us.among.model.header;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.util.AppUtil.checkArgument;

/**
 * Represents a Header in the API endpoint list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidHeaderName(String)}
 */
public class Header {

    public static final String MESSAGE_CONSTRAINTS = "Headers should be in the \"key: value\" format.";
    public static final String VALIDATION_REGEX = ".*";

    public final String headerName;

    /**
     * Constructs a {@code Header}.
     *
     * @param headerName A valid tag name.
     */
    public Header(String headerName) {
        requireNonNull(headerName);
        checkArgument(isValidHeaderName(headerName), Header.MESSAGE_CONSTRAINTS);
        this.headerName = headerName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidHeaderName(String test) {
        String headerString = test.strip().toString();
        String[] headerPair = headerString.split(":", 2);
        //Checks if there is a ":" in the data entered.
        if (headerPair.length != 2) {
            return false;
        }
        //Checks if header is enclosed with ""
        if (headerString.startsWith("\"") && headerString.endsWith("\"")) {
            return test.matches(VALIDATION_REGEX);
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Header // instanceof handles nulls
                && headerName.equals(((Header) other).headerName)); // state check
    }

    @Override
    public int hashCode() {
        return headerName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return headerName.substring(1, headerName.length() - 1);
    }

}
