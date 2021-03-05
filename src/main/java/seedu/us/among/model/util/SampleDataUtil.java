package seedu.us.among.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Name;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EndpointList} with sample data.
 */
public class SampleDataUtil {
    public static Endpoint[] getSamplePersons() {
        return new Endpoint[] {
            new Endpoint(new Name("Alex Yeoh"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new Response()),
            new Endpoint(new Name("Bernice Yu"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new Response()),
            new Endpoint(new Name("Charlotte Oliveiro"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new Response()),
            new Endpoint(new Name("David Li"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new Response()),
            new Endpoint(new Name("Irfan Ibrahim"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new Response()),
            new Endpoint(new Name("Roy Balakrishnan"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new Response())
        };
    }

    public static ReadOnlyEndpointList getSampleEndpointList() {
        EndpointList sampleAb = new EndpointList();
        for (Endpoint sampleEndpoint : getSamplePersons()) {
            sampleAb.addEndpoint(sampleEndpoint);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
