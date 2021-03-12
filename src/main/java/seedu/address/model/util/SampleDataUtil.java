package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTaskTracker;
import seedu.address.model.TaskTracker;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleName;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Task;
import seedu.address.model.person.Weightage;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TaskTracker} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Task[] getSampleTasks() {
        return new Task[]{
            new Task(new ModuleName("Alex Yeoh"), new ModuleCode("CS2103"), new Weightage(0),
                    new Phone("87438807"),
                    EMPTY_REMARK, getTagSet("friends")),
            new Task(new ModuleName("Bernice Yu"), new ModuleCode("CS2040"), new Weightage(0),
                    new Phone("99272758"),
                    EMPTY_REMARK, getTagSet("colleagues", "friends")),
            new Task(new ModuleName("Charlotte Oliveiro"), new ModuleCode("CS1010"), new Weightage(0),
                    new Phone("93210283"),
                    EMPTY_REMARK, getTagSet("neighbours")),
            new Task(new ModuleName("David Li"), new ModuleCode("CS2030"), new Weightage(0),
                    new Phone("91031282"),
                    EMPTY_REMARK, getTagSet("family")),
            new Task(new ModuleName("Irfan Ibrahim"), new ModuleCode("CS3240"), new Weightage(0),
                    new Phone("92492021"),
                    EMPTY_REMARK, getTagSet("classmates")),
            new Task(new ModuleName("Roy Balakrishnan"), new ModuleCode("CS2103"), new Weightage(0),
                    new Phone("92624417"),
                    EMPTY_REMARK, getTagSet("colleagues"))
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
