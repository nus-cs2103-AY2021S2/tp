package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Planner;
import seedu.address.model.ReadOnlyPlanner;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Email;
import seedu.address.model.task.Phone;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Contains utility methods for populating {@code Planner} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Title("Assignment 79"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Description("Build the next Google"),
                getTagSet("priorities")),
            new Task(new Title("Build a gaming PC"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Description("buy: coffee, 3080, 40-inch monitor"),
                getTagSet("findMoney", "priorities")),
            new Task(new Title("Countdown to finals"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Description("aiken doeet"),
                getTagSet("trying")),
            new Task(new Title("Demo our amazing product"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Description("Number 1 for real"),
                getTagSet("fact")),
            new Task(new Title("Ensure that I eat lunch"), new Phone("92492021"), new Email("irfan@example.com"),
                new Description("budget 3$"),
                getTagSet("reminder")),
            new Task(new Title("Final project consult again"), new Phone("92624417"), new Email("royb@example.com"),
                new Description("at COM2"),
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
