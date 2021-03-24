package seedu.module.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.module.model.tag.Tag;
import seedu.module.model.task.Time;
import seedu.module.model.task.Description;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Task;
import seedu.module.model.task.Workload;
import seedu.module.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Assignment 6";
    public static final String DEFAULT_START_TIME = "2021-03-07 12:00";
    public static final String DEFAULT_DEADLINE = "2021-03-07 14:00";
    public static final String DEFAULT_MODULE = "CS3243";
    public static final String DEFAULT_DONE = String.valueOf(Boolean.FALSE);
    public static final String DEFAULT_DESCRIPTION = "Not very hard.";
    public static final String DEFAULT_WORKLOAD = "1";

    private Name name;
    private Time deadline;
    private Module module;
    private Description description;
    private Workload workload;
    private DoneStatus doneStatus;

    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        deadline = new Time(DEFAULT_DEADLINE);
        module = new Module(DEFAULT_MODULE);
        description = new Description(DEFAULT_DESCRIPTION);
        workload = new Workload(DEFAULT_WORKLOAD);
        doneStatus = new DoneStatus(DEFAULT_DONE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
        module = taskToCopy.getModule();
        description = taskToCopy.getDescription();
        workload = taskToCopy.getWorkload();
        doneStatus = taskToCopy.getDoneStatus();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code DESCRIPTION} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Time(deadline);
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Task} that we are building.
     */
    public TaskBuilder withModule(String module) {
        this.module = new Module(module);
        return this;
    }

    /**
     * Sets the {@code Workload} of the {@code Task} that we are building.
     */
    public TaskBuilder withWorkload(String workload) {
        this.workload = new Workload(workload);
        return this;
    }

    /**
     * Sets the {@code DoneStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withDoneStatus(String doneStatus) {
        this.doneStatus = new DoneStatus(doneStatus);
        return this;
    }

    public Task build() {
        return new Task(name, deadline, module, description, workload, doneStatus, tags);
    }

}
