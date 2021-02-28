package seedu.us.among.testutil;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * A utility class to help with building EndpointList objects.
 * Example usage: <br>
 *     {@code EndpointList ab = new EndpointListBuilder().withEndpoint("John", "Doe").build();}
 */
public class EndpointListBuilder {

    private EndpointList endpointList;

    public EndpointListBuilder() {
        endpointList = new EndpointList();
    }

    public EndpointListBuilder(EndpointList endpointList) {
        this.endpointList = endpointList;
    }

    /**
     * Adds a new {@code Endpoint} to the {@code EndpointList} that we are building.
     */
    public EndpointListBuilder withEndpoint(Endpoint endpoint) {
        endpointList.addEndpoint(endpoint);
        return this;
    }

    public EndpointList build() {
        return endpointList;
    }
}
