package seedu.address.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTaskTracker;
import seedu.address.model.TaskTracker;
import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineTime;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Status;
import seedu.address.model.person.Task;
import seedu.address.model.person.TaskName;
import seedu.address.model.person.Weightage;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TaskTracker} with sample data.
 */
public class SampleDataUtil {

    public static final Notes EMPTY_NOTES = new Notes("");
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    public static final DeadlineDate DEFAULT_DATE = new DeadlineDate(
            LocalDate.now().plusDays(5).format(FORMATTER)); // 5 days after today

    public static Task[] getSampleTasks() {
        return new Task[]{
            new Task(new TaskName("Week 10 Quiz"), new ModuleCode("CS2103"),
                    DEFAULT_DATE, new DeadlineTime("10:10"),
                new Status(), new Weightage(0),
                    EMPTY_NOTES, getTagSet("core")),
            new Task(new TaskName("Take Home lab 1"), new ModuleCode("CS2040"),
                    DEFAULT_DATE, new DeadlineTime("10:10"),
                new Status(), new Weightage(10),
                    EMPTY_NOTES, getTagSet("core", "difficult")),
            new Task(new TaskName("Tutorial 6"), new ModuleCode("CS1010"),
                    DEFAULT_DATE, new DeadlineTime("10:10"),
                new Status("Finished"), new Weightage(20),
                    EMPTY_NOTES, getTagSet("core")),
            new Task(new TaskName("Tutorial 5"), new ModuleCode("CS2030"),
                    DEFAULT_DATE, new DeadlineTime("10:10"),
                new Status(), new Weightage(10),
                    EMPTY_NOTES, getTagSet("core")),
            new Task(new TaskName("Weekly Readings"), new ModuleCode("CS3243"),
                    DEFAULT_DATE, new DeadlineTime("10:10"),
                new Status(), new Weightage(20),
                    EMPTY_NOTES, getTagSet("specialization")),
            new Task(new TaskName("Write Tests"), new ModuleCode("CS3244"),
                    DEFAULT_DATE, new DeadlineTime("10:10"),
                new Status("Finished"), new Weightage(15),
                    EMPTY_NOTES, getTagSet("specialization"))
        };
    }


    public static ReadOnlyTaskTracker getSampleTaskTracker() {
        TaskTracker sampleAb = new TaskTracker();

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
