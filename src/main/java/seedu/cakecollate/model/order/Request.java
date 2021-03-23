package seedu.cakecollate.model.order;

import static java.util.Objects.requireNonNull;

public class Request {

    public final String value;

    /**
     * Constructs a {@code Request}.
     *
     * @param request The request.
     */
    public Request(String request) {
        requireNonNull(request);
        value = request;
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
