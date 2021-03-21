package seedu.taskify.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.taskify.model.ReadOnlyTaskify;
import seedu.taskify.model.Taskify;
import seedu.taskify.model.tag.Tag;
import seedu.taskify.model.task.Date;
import seedu.taskify.model.task.Description;
import seedu.taskify.model.task.Name;
import seedu.taskify.model.task.Status;
import seedu.taskify.model.task.Task;

/**
 * Contains utility methods for populating {@code TaskifyParser} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[]{
            new Task(new Name("CS2106 lab"), new Description("on synchronization"), new Status(),
                    new Date("2020-04-13 09:30"),
                    getTagSet("friends")),
            new Task(new Name("Bernice Yu"), new Description("2106 lab on threads"), new Status(),
                    new Date("2020-04-13 09:30"),
                    getTagSet("colleagues", "friends")),
            new Task(new Name("Charlotte Oliveiro"), new Description("2106 lab on threads"),
                    new Status(), new Date("2020-04-13 09:30"), getTagSet("neighbours")),
            new Task(new Name("David Li"), new Description("2106 lab on threads"), new Status(),
                    new Date("2020-04-13 09:30"),
                    getTagSet("family")),
            new Task(new Name("Irfan Ibrahim"), new Description("2106 lab on threads"), new Status(),
                    new Date("2020-04-13 09:30"),
                    getTagSet("classmates")),
            new Task(new Name("Roy Balakrishnan"), new Description("2106 lab on threads"),
                    new Status(), new Date("2020-04-13 09:30"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyTaskify getSampleAddressBook() {
        Taskify sampleAb = new Taskify();
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
