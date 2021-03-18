package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.common.Category;
import seedu.address.model.common.Tag;

//import seedu.address.model.Sochedule;
//import seedu.address.model.ReadOnlySochedule;
//import seedu.address.model.common.Name;
//import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    //    public static Task[] getSampleTasks() {
    //
    //    }

    //    public static ReadOnlySochedule getSampleSochedule() {
    //
    //    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a category set containing the list of strings given.
     */
    public static Set<Category> getCategorySet(String... strings) {
        return Arrays.stream(strings)
                .map(Category::new)
                .collect(Collectors.toSet());
    }

}
