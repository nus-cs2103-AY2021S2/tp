package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.residence.BookingDetails;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Residence[] getSampleResidence() {
        return new Residence[]{
            new Residence(new ResidenceName("HDB"), new ResidenceAddress("Blk 30 Geylang Street 29, #06-40"),
                    new BookingDetails("someone is coming"), getCleanStatusTagSet("n"), getTagSet("booked")),
        };
    }

    public static ReadOnlyResidenceTracker getSampleAddressBook() {
        ResidenceTracker sampleAb = new ResidenceTracker();
        for (Residence sampleResidence : getSampleResidence()) {
            sampleAb.addResidence(sampleResidence);
        }
        return sampleAb;
    }

    /**
     * Returns a CleanStatusTag set containing the list of strings given.
     */
    public static Set<CleanStatusTag> getCleanStatusTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(CleanStatusTag::new)
                .collect(Collectors.toSet());
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
