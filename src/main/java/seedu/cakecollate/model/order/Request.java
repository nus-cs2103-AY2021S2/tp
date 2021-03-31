package seedu.cakecollate.model.order;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Order's request in CakeCollate.
 * Guarantees: immutable; is always valid
 */
public class Request {

    public final String value;
    public final boolean isRequestEmpty;

    /**
     * Constructs a {@code Request}.
     *
     * @param request The request.
     */
    public Request(String request) {
        requireNonNull(request);
        if (request.isEmpty()) {
            isRequestEmpty = true;
            value = "No special requests added yet.";
        } else {
            isRequestEmpty = false;
            value = request;
        }
    }

    public boolean isRequestEmpty() {
        return isRequestEmpty;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Request // instanceof handles nulls
                && value.equals(((Request) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
