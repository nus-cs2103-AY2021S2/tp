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
            new Endpoint(new Method("GET"), new Address("Blk 30 Geylang Street 29, #06-40"), new Data("{sample data}"),
                    getHeaderSet("{'key': 'value'}"), getTagSet("friends"), new Response()),
            new Endpoint(new Method("POST"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Data("{'key': 'value'}"), getHeaderSet("sampleheader"),
                    getTagSet("colleagues", "friends"), new Response()),
            new Endpoint(new Method("HEAD"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Data("{'key': 'value'}"), getHeaderSet("sampleheader"),
                    getTagSet("neighbours"), new Response()),
            new Endpoint(new Method("DELETE"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Data("{'key': 'value'}"), getHeaderSet("sampleheader"),
                    getTagSet("family"), new Response()),
            new Endpoint(new Method("OPTIONS"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Data("{'key': 'value'}"), getHeaderSet("sampleheader"),
                    getTagSet("classmates"), new Response()),
            new Endpoint(new Method("GET"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Data("{'key': 'value'}"), getHeaderSet("sampleheader"),
                    getTagSet("colleagues"), new Response()) };
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
