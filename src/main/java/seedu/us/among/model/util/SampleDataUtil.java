package seedu.us.among.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Name;
import seedu.us.among.model.endpoint.Phone;
import seedu.us.among.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EndpointList} with sample data.
 */
public class SampleDataUtil {
    public static Endpoint[] getSamplePersons() {
        return new Endpoint[] {
            new Endpoint(new Name("Alex Yeoh"), new Phone("87438807"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Endpoint(new Name("Bernice Yu"), new Phone("99272758"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Endpoint(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Endpoint(new Name("David Li"), new Phone("91031282"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Endpoint(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Endpoint(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
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
