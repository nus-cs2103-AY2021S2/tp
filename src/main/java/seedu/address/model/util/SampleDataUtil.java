package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ResidenceTracker} with sample data.
 */
public class SampleDataUtil {
    public static Residence[] getSampleResidence() {
        return new Residence[]{
            new Residence(new ResidenceName("HDB"), new ResidenceAddress("Blk 30 Geylang Street 29, #06-40"),
                    new BookingList("someone is coming"), getCleanStatusTag("y"), getTagSet("Booked")),
            new Residence(new ResidenceName("Condo"), new ResidenceAddress("Blk 45 Tampines Street 29, #08-01"),
                    new BookingList("4 adults"), getCleanStatusTag("n"), getTagSet("Reserved")),
        };
    }

    public static ReadOnlyResidenceTracker getSampleResidenceTracker() {
        ResidenceTracker sampleRt = new ResidenceTracker();
        for (Residence sampleResidence : getSampleResidence()) {
            sampleRt.addResidence(sampleResidence);
        }
        return sampleRt;
    }

    /**
     * Returns a CleanStatusTag containing the list of strings given.
     */
    public static CleanStatusTag getCleanStatusTag(String status) {
        return new CleanStatusTag(status);
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
