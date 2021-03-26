package seedu.module.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.module.model.ModuleBook;
import seedu.module.model.ReadOnlyModuleBook;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Description;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Task;
import seedu.module.model.task.Time;
import seedu.module.model.task.Workload;

/**
 * Contains utility methods for populating {@code ModuleBook} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Midterm"), new Time("2021-03-07 08:30"),
                    new Time("2021-03-07 10:30"), new Module("CS3243"),
                    new Description("Not include CSP."), new Workload("3"),
                    new DoneStatus(false), getTagSet("highPriority")),
            new Task(new Name("Team Project"), new Time("2021-03-15 14:00"),
                    new Time("2021-03-15 16:00"), new Module("CS2103T"),
                    new Description("Wrap up version 1.2."), new Workload("3"),
                    new DoneStatus(true), getTagSet())
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
