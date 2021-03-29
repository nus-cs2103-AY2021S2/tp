package seedu.plan.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.plan.model.ModulePlanner;
import seedu.plan.model.ReadOnlyModulePlanner;
import seedu.plan.model.plan.Description;
import seedu.plan.model.plan.Plan;
import seedu.plan.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Plan[] getSamplePersons() {
        return new Plan[] {
            new Plan(new Description("Initial Plan"),
                getTagSet("sample")),
        };
    }

    public static ReadOnlyModulePlanner getSampleModulePlanner() {
        ModulePlanner sampleAb = new ModulePlanner();
        for (Plan samplePlan : getSamplePersons()) {
            sampleAb.addPlan(samplePlan);
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
