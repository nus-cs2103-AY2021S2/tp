package seedu.iscam.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.iscam.model.ClientBook;
import seedu.iscam.model.ReadOnlyClientBook;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;

/**
 * Contains utility methods for populating {@code ClientBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[]{
            new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Location("Blk 30 Geylang Street 29, #06-40"), new InsurancePlan("Plan A"),
                    getTagSet("friends")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Location("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new InsurancePlan("Plan B"),
                    getTagSet("colleagues", "friends")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Location("Blk 11 Ang Mo Kio Street 74, #11-04"), new InsurancePlan("Plan C"),
                    getTagSet("neighbours")),
            new Client(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Location("Blk 436 Serangoon Gardens Street 26, #16-43"), new InsurancePlan("Plan B"),
                    getTagSet("family")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Location("Blk 47 Tampines Street 20, #17-35"), new InsurancePlan("Plan A"),
                    getTagSet("classmates")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Location("Blk 45 Aljunied Street 85, #11-31"), new InsurancePlan("Plan A"),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyClientBook getSampleClientBook() {
        ClientBook sampleAb = new ClientBook();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
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
