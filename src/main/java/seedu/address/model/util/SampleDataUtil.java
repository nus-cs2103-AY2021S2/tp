package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Planner;
import seedu.address.model.ReadOnlyPlanner;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Email;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Contains utility methods for populating {@code Planner} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Title("Assignment 79"), new Deadline("87438807"), new Email("alexyeoh@example.com"),
                new Description("Build the next Google"), new Status("done"),
                getTagSet("priorities")),
            new Task(new Title("Build a gaming PC"), new Deadline("99272758"), new Email("berniceyu@example.com"),
                new Description("buy: coffee, 3080, 40-inch monitor"), new Status("done"),
                getTagSet("findMoney", "priorities")),
            new Task(new Title("Countdown to finals"), new Deadline("93210283"), new Email("charlotte@example.com"),
                new Description("aiken doeet"), new Status("done"),
                getTagSet("trying")),
            new Task(new Title("Demo our amazing product"), new Deadline("91031282"), new Email("lidavid@example.com"),
                new Description("Number 1 for real"), new Status("done"),
                getTagSet("fact")),
            new Task(new Title("Ensure that I eat lunch"), new Deadline("92492021"), new Email("irfan@example.com"),
                new Description("budget 3$"), new Status("done"),
                getTagSet("reminder")),
            new Task(new Title("Final project consult again"), new Deadline("92624417"), new Email("royb@example.com"),
                new Description("at COM2"), new Status("done"),
                getTagSet("serious"))
        };
    }

    public static ReadOnlyPlanner getSamplePlanner() {
        Planner sampleAb = new Planner();
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
