package seedu.us.among.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.endpoint.header.Header;
import seedu.us.among.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EndpointList} with sample
 * data.
 */
public class SampleDataUtil {
    public static Endpoint[] getSampleEndpoint() {
        return new Endpoint[] {
            new Endpoint(new Method("GET"),
                    new Address("https://api.data.gov.sg/v1/environment/2-hour-weather-forecast"), new Data("{}"),
                    getHeaderSet("accept: application/json"), getTagSet("singapore"), new Response()),
            new Endpoint(new Method("GET"), new Address("https://api.data.gov.sg/v1/transport/taxi-availability"),
                    new Data("{}"), getHeaderSet("accept: application/vnd.geo+json"),
                    getTagSet("singapore", "transport"), new Response()),
            new Endpoint(new Method("GET"),
                    new Address("https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=2"),
                    new Data("{'key': 'value'}"), getHeaderSet("sampleheader"),
                    getTagSet("cats"), new Response())};
    }

    public static ReadOnlyEndpointList getSampleEndpointList() {
        EndpointList sampleAb = new EndpointList();
        for (Endpoint sampleEndpoint : getSampleEndpoint()) {
            sampleAb.addEndpoint(sampleEndpoint);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

    /**
     * Returns a header set containing the list of strings given.
     */
    public static Set<Header> getHeaderSet(String... strings) {
        return Arrays.stream(strings).map(Header::new).collect(Collectors.toSet());
    }

}
