package seedu.us.among.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.header.Header;
import seedu.us.among.model.tag.Tag;
import seedu.us.among.model.util.SampleDataUtil;

/**
 * A utility class to help with building Endpoint objects.
 */
public class EndpointBuilder {

    public static final String DEFAULT_NAME = "GET";
    public static final String DEFAULT_ADDRESS = "sample/address";
    public static final String DEFAULT_DATA = "{defaultdata}";

    private Method method;
    private Address address;
    private Data data;
    private Set<Header> headers;
    private Set<Tag> tags;

    /**
     * Creates a {@code EndpointBuilder} with the default details.
     */
    public EndpointBuilder() {
        method = new Method(DEFAULT_NAME);
        address = new Address(DEFAULT_ADDRESS);
        data = new Data();
        //data = new Data(DEFAULT_DATA);
        headers = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the EndpointBuilder with the data of {@code endpointToCopy}.
     */
    public EndpointBuilder(Endpoint endpointToCopy) {
        method = endpointToCopy.getMethod();
        address = endpointToCopy.getAddress();
        data = endpointToCopy.getData();
        headers = new HashSet<>(endpointToCopy.getHeaders());
        tags = new HashSet<>(endpointToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Endpoint} that we are building.
     */
    public EndpointBuilder withMethod(String name) {
        this.method = new Method(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Endpoint} that we are building.
     */
    public EndpointBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code headers} into a {@code Set<Header>} and set it to the
     * {@code Endpoint} that we are building.
     */
    public EndpointBuilder withHeaders(String... headers) {
        this.headers = SampleDataUtil.getHeaderSet(headers);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Endpoint} that we are building.
     */
    public EndpointBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Data} of the {@code Endpoint} that we are building.
     */
    public EndpointBuilder withData(String data) {
        this.data = new Data(data);
        return this;
    }

    public Endpoint build() {
        return new Endpoint(method, address, data, headers, tags);
    }

}
