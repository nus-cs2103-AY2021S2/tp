package seedu.us.among.model.endpoint;

import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.us.among.model.endpoint.header.Header;
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
    private final Data data;
    private final Set<Header> headers = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    // Response fields
    private final Response response;

    /**
     * Constructor for endpoint, with empty response
     */
    public Endpoint(Method method, Address address, Data data, Set<Header> headers, Set<Tag> tags) {
        requireAllNonNull(method, address, data, headers, tags);
        this.method = method;
        this.address = address;
        this.data = data;
        this.headers.addAll(headers);
        this.tags.addAll(tags);
        this.response = new Response();
    }

    /**
     * Constructor for endpoint, with specific response and data
     */
    public Endpoint(Method method, Address address, Data data, Set<Header> headers, Set<Tag> tags, Response response) {
        requireAllNonNull(method, address, data, headers, tags, response);
        this.method = method;
        this.address = address;
        this.data = data;
        this.headers.addAll(headers);
        this.tags.addAll(tags);
        this.response = response;
    }

    /**
     * Constructor for endpoint, with empty response and data
     */
    public Endpoint(Method method, Address address, Set<Header> headers, Set<Tag> tags) {
        requireAllNonNull(method, address, headers, tags);
        this.method = method;
        this.address = address;
        this.data = new Data();
        this.headers.addAll(headers);
        this.tags.addAll(tags);
        this.response = new Response();
    }


    /**
     * Constructor for endpoint, with data
     */
    public Endpoint(Method method, Address address, Set<Header> headers, Set<Tag> tags, Response response) {
        requireAllNonNull(method, address, headers, tags, response);
        this.method = method;
        this.address = address;
        this.data = new Data();
        this.headers.addAll(headers);
        this.tags.addAll(tags);
        this.response = response;
    }

    /**
     * Constructor for endpoint, with the details of {@code otherEndpoint} and response
     */
    public Endpoint(Endpoint otherEndpoint, Response response) {
        this(otherEndpoint.getMethod(),
                otherEndpoint.getAddress(),
                otherEndpoint.getData(),
                otherEndpoint.getHeaders(),
                otherEndpoint.getTags(),
                response);
    }

    /**
     * Constructor for a default GET request endpoint, with the address
     */
    public Endpoint(Address address) {
        this(new Method("GET"), address, new HashSet<>(), new HashSet<>());
    }

    public Method getMethod() {
        return method;
    }

    public Address getAddress() {
        return address;
    }

    public Data getData() {
        return data;
    }

    public Response getResponse() {
        return response;
    }

    public MethodType getMethodType() {
        return method.getMethodType();
    }

    public String getReasonPhrase() {
        return response.getReasonPhrase();
    }

    public String getResponseEntity() {
        return response.getResponseEntity();
    }

    /**
     * Returns an immutable header set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Header> getHeaders() {
        return Collections.unmodifiableSet(headers);
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both methods have the same identity and data fields.
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
        return otherEndpoint.getMethod().equals(getMethod())
                && otherEndpoint.getAddress().equals(getAddress())
                && otherEndpoint.getData().equals(getData())
                && otherEndpoint.getHeaders().equals(getHeaders())
                && otherEndpoint.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(method, address, data, headers, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Method:\n")
                .append(getMethod())
                .append("\nAddress:\n")
                .append(getAddress());

        if (!data.isEmpty()) {
            builder.append("\nData:\n")
                    .append(getData());
        }

        Set<Header> headers = getHeaders();
        if (!headers.isEmpty()) {
            builder.append("\nHeaders:\n");
            headers.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags:\n");
            tags.forEach(builder::append);
        }

        if (response != null) {
            builder.append("\nLast Response:\n")
                    .append(getResponse());
        }
        return builder.toString();
    }

}
