package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.plan.Description;
import seedu.address.model.plan.Plan;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Plan[] getSamplePersons() {
        return new Plan[] {
            new Plan(new Description("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Plan(new Description("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Plan(new Description("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Plan(new Description("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Plan(new Description("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Plan(new Description("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Plan samplePlan : getSamplePersons()) {
            sampleAb.addPerson(samplePlan);
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
