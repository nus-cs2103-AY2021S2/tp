package seedu.us.among.model.endpoint;

import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.us.among.model.tag.Tag;

/**
 * Represents a Endpoint in the address book. Guarantees: details are present
 * and not null, field values are validated, immutable.
 */
public class Endpoint {

    // Identity fields
    private final Method method;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    // Response fields
    private final Response response;

    /**
     * Every field must be present and not null.
     */
    public Endpoint(Method method, Address address, Set<Tag> tags) {
        requireAllNonNull(method, address, tags);
        this.method = method;
        this.address = address;
        this.tags.addAll(tags);
        this.response = new Response();
    }

    /**
     * Every field must be present and not null.
     */
    public Endpoint(Method method, Address address, Set<Tag> tags, Response response) {
        requireAllNonNull(method, address, tags, response);
        this.method = method;
        this.address = address;
        this.tags.addAll(tags);
        this.response = response;
    }

    public Method getMethod() {
        return method;
    }

    public Address getAddress() {
        return address;
    }

    public Response getResponse() {
        return response;
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both methods have the same method and address. This defines a weaker
     * notion of equality between two methods.
     */
    public boolean isSameEndpoint(Endpoint otherEndpoint) {
        if (otherEndpoint == this) {
            return true;
        }
        return otherEndpoint != null && otherEndpoint.getMethod().equals(getMethod())
                && otherEndpoint.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both methods have the same identity and data fields. This
     * defines a stronger notion of equality between two methods.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Endpoint)) {
            return false;
        }

        Endpoint otherEndpoint = (Endpoint) other;
        return otherEndpoint.getMethod().equals(getMethod()) && otherEndpoint.getAddress().equals(getAddress())
                && otherEndpoint.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(method, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getMethod()).append("; Address: ").append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (response != null) {
            builder.append("; Last Response: ")
                    .append(getResponse());
        }
        return builder.toString();
    }

}
