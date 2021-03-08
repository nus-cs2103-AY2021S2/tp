package seedu.us.among.model.endpoint.header;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.util.AppUtil.checkArgument;

/**
 * Represents a Header in the API endpoint list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidHeaderName(String)}
 */
public class Header {
    /**
     * to-do
     * THIS WHOLE CLASS IS A TO-DO I JUST COPY PASTED TAGS
     */

    public static final String MESSAGE_CONSTRAINTS = "Header names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String headerName;

    /**
     * Constructs a {@code Header}.
     *
     * @param headerName A valid tag name.
     */
    public Header(String headerName) {
        requireNonNull(headerName);
        checkArgument(isValidHeaderName(headerName), MESSAGE_CONSTRAINTS);
        this.headerName = headerName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidHeaderName(String test) {
        return test.matches(VALIDATION_REGEX);
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
        return '[' + headerName + ']';
    }

}
