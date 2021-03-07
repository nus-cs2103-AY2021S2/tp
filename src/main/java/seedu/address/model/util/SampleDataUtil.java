package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ModuleBook;
import seedu.address.model.ReadOnlyModuleBook;
import seedu.address.model.task.*;
import seedu.address.model.task.Module;
import seedu.address.model.task.Task;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ModuleBook} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Midterm"), new Deadline("2021-03-07 08:30"), new Module("CS3243"),
                new Address("Not include CSP."),
                getTagSet("highPriority")),
            new Task(new Name("Team Project"), new Deadline("2021-03-15 16:00"), new Module("CS2103T"),
                new Address("Wrap up version 1.2."), getTagSet())
        };
    }

    public static ReadOnlyModuleBook getSampleModuleBook() {
        ModuleBook sampleAb = new ModuleBook();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
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
