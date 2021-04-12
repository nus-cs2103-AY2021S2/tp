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
import seedu.taskify.model.task.StatusType;
import seedu.taskify.model.task.Task;

/**
 * Contains utility methods for populating {@code TaskifyParser} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[]{
            new Task(new Name("Buy groceries"), new Description("Refer to grocery list!"),
                    new Status(StatusType.UNCOMPLETED), new Date("2021-04-20 09:30"),
                    getTagSet("Grocery")),
            new Task(new Name("Group project meeting"), new Description("Prepare material before meeting"),
                    new Status(StatusType.COMPLETED), new Date("2021-04-21 20:30"),
                    getTagSet("University")),
            new Task(new Name("CS2103T Tutorial"), new Description("Submit on Luminus"),
                    new Status(StatusType.UNCOMPLETED), new Date("2021-04-16 23:59"),
                    getTagSet("University", "CS2103T")),
            new Task(new Name("Internship interview"), new Description("For Shopee"),
                    new Status(StatusType.UNCOMPLETED), new Date("2021-04-15 15:30"),
                    getTagSet("Internship")),
            new Task(new Name("CS2101 presentation"), new Description("Prepare script"),
                    new Status(StatusType.COMPLETED), new Date("2021-04-20 08:00"),
                    getTagSet("University", "CS2101")),
            new Task(new Name("Prepare for CS2106 quiz"), new Description("Topic includes threads"),
                    new Status(StatusType.UNCOMPLETED), new Date("2021-04-15 20:00"),
                    getTagSet("University", "CS2106"))
        };
    }

    public static ReadOnlyTaskify getSampleTaskifyData() {
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
