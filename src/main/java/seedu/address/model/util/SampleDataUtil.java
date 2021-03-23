package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Planner;
import seedu.address.model.ReadOnlyPlanner;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Contains utility methods for populating {@code Planner} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Title("Assignment 79"), new Deadline("13/12/2021"), new StartTime("12:30"),
                    new RecurringSchedule("[10/06/2021][Mon][biweekly]"), new Description("Build the next Google"),
                    new Status("not done"), getTagSet("priorities")),
            new Task(new Title("Build a gaming PC"), new Deadline("12/12/2021"), new StartTime("12:30"),
                    new RecurringSchedule("[08/06/2021][Tue][weekly]"),
                    new Description("buy: coffee, \n3080, \n40-inch monitor"),
                    new Status("done"), getTagSet("findMoney", "priorities")),
            new Task(new Title("Countdown to finals"), new Deadline("09/09/2021"), new StartTime("12:30"),
                    new RecurringSchedule("[10/06/2021][Wed][weekly]"), new Description("aiken doeet"),
                    new Status("done"), getTagSet("trying")),
            new Task(new Title("Demo our amazing product"), new Deadline("25/09/2021"), new StartTime("12:30"),
                    new RecurringSchedule("[05/06/2021][Thu][biweekly]"), new Description("Number 1 for real"),
                    new Status("done"), getTagSet("fact")),
            new Task(new Title("Ensure that I eat lunch"), new Deadline("20/10/2021"), new StartTime("12:30"),
                    new RecurringSchedule("[02/06/2021][Wed][weekly]"), new Description("budget 3$"),
                    new Status("done"), getTagSet("reminder")),
            new Task(new Title("Final project consult again"), new Deadline("20/10/2021"), new StartTime("12:30"),
                    new RecurringSchedule("[03/06/2021][Fri][biweekly]"), new Description("at COM2"),
                    new Status("done"), getTagSet("serious"))
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
