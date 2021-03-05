package seedu.us.among.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EndpointList} with sample
 * data.
 */
public class SampleDataUtil {
    public static Endpoint[] getSampleEndpoint() {
        return new Endpoint[] {
            new Endpoint(new Method("GET"), new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends")),
            new Endpoint(new Method("POST"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Endpoint(new Method("HEAD"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Endpoint(new Method("DELETE"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
            new Endpoint(new Method("OPTIONS"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates")),
            new Endpoint(new Method("GET"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues")) };
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

}
