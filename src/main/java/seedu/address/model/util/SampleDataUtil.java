package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Planner;
import seedu.address.model.ReadOnlyPlanner;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.attributes.Description;
import seedu.address.model.task.attributes.Duration;
import seedu.address.model.task.attributes.RecurringSchedule;
import seedu.address.model.task.attributes.Status;
import seedu.address.model.task.attributes.Title;
/**
 * Contains utility methods for populating {@code Planner} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Title("Assignment 3"), new Date("13/05/2021"), new Duration(""),
                    new RecurringSchedule(""), new Description("Commands: bash test.sh"),
                    new Status("done"), getTagSet("CS2105", "Assignment")),
            new Task(new Title("CS2103 Team Meeting"), new Date(""), new Duration("12:30-18:30"),
                    new RecurringSchedule("[08/06/2021][Mon][weekly]"),
                    new Description("Check feature updates with teammates\nFinalise UI layout, \nStandardise UG & DG"),
                    new Status("not done"), getTagSet("CS2103", "Project")),
            new Task(new Title("Countdown to finals"), new Date("09/05/2021"), new Duration(""),
                    new RecurringSchedule(""), new Description("aiken doeet"),
                    new Status("not done"), getTagSet("Finals")),
            new Task(new Title("Demo our amazing product"), new Date("25/05/2021"), new Duration("12:30-14:30"),
                    new RecurringSchedule(""), new Description("Product demo to NUS Computing Students"),
                    new Status("not done"), getTagSet("CS2103", "Project")),
            new Task(new Title("Ensure that I eat lunch"), new Date(""), new Duration("12:30-13:30"),
                    new RecurringSchedule("[02/06/2021][Wed][weekly]"), new Description("budget $3"),
                    new Status("not done"), getTagSet("reminder")),
            new Task(new Title("Final project consultation"), new Date(""), new Duration("12:30-13:30"),
                    new RecurringSchedule("[03/06/2021][Fri][biweekly]"), new Description("Location at COM2"),
                    new Status("not done"), getTagSet("CS2103", "Project")),
            new Task(new Title("CS2103 last milestone"), new Date("04/06/2021"), new Duration(""),
                   new RecurringSchedule(""), new Description("Wrap up project and release jar file"),
                   new Status("done"), getTagSet("CS2103", "Project")),
            new Task(new Title("CS2102 NFR"), new Date("30/05/2021"), new Duration(""),
                   new RecurringSchedule(""), new Description("Pls give me mercy"),
                   new Status("not done"), getTagSet("CS2102", "Project")),
            new Task(new Title("CS2105 Assignment 3"), new Date("15/05/2021"), new Duration(""),
                    new RecurringSchedule(""), new Description("Beware of Youtube Ban"),
                    new Status("not done"), getTagSet("CS2105", "Assignment")),
            new Task(new Title("CS2105 Lecture"), new Date(""), new Duration(""),
                    new RecurringSchedule("[30/05/2021][thu][weekly]"), new Description("Mr Zimmerman Media sharing"),
                    new Status("not done"), getTagSet("CS2105", "Lectures")),
            new Task(new Title("CS2106 Lecture"), new Date(""), new Duration(""),
                     new RecurringSchedule("[30/05/2021][tue][weekly]"), new Description("Pipelining and Threading!!!"),
                     new Status("not done"), getTagSet("CS2106", "Lectures")),
            new Task(new Title("CS2102 Lecture"), new Date(""), new Duration(""),
                        new RecurringSchedule("[30/05/2021][fri][weekly]"), new Description("SQL never ending queries"),
                        new Status("not done"), getTagSet("CS2102", "Lectures")),
            new Task(new Title("CS2103 PE"), new Date(""), new Duration(""),
                    new RecurringSchedule(""), new Description("Time to find bugs!!!"),
                    new Status("not done"), getTagSet("CS2103")),
        };
    }

    public static ReadOnlyPlanner getSamplePlanner() {
        Planner sampleAb = new Planner();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
            sampleAb.addTagsIfAbsent(sampleTask.getTags());
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
