package seedu.module.testutil;

import static seedu.module.model.task.Time.DATE_TIME_FORMATTER_WITH_TIME;

import java.util.HashSet;
import java.util.Set;

import seedu.module.commons.core.optionalfield.OptionalField;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Description;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Recurrence;
import seedu.module.model.task.Task;
import seedu.module.model.task.Time;
import seedu.module.model.task.Workload;
import seedu.module.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Assignment 6";
    public static final String LATER_STARTTIME = "2021-03-09 14:00";
    public static final String DEFAULT_DEADLINE = "2021-03-07 14:00";
    public static final String DEFAULT_MODULE = "CS3243";
    public static final String DEFAULT_DONE = String.valueOf(Boolean.FALSE);
    public static final String DEFAULT_DESCRIPTION = "Not very hard.";
    public static final String DEFAULT_WORKLOAD = "1";
    public static final String DEFAULT_RECURRENCE = "biweekly";

    private Name name;
    private OptionalField<Time> startTime;
    private Time deadline;
    private Module module;
    private Description description;
    private Workload workload;
    private DoneStatus doneStatus;
    private OptionalField<Recurrence> recurrence;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        startTime = new OptionalField<>(null);
        deadline = new Time(DEFAULT_DEADLINE);
        module = new Module(DEFAULT_MODULE);
        description = new Description(DEFAULT_DESCRIPTION);
        workload = new Workload(DEFAULT_WORKLOAD);
        doneStatus = new DoneStatus(DEFAULT_DONE);
        recurrence = new OptionalField<>(null);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        startTime = taskToCopy.getStartTimeWrapper();
        deadline = taskToCopy.getDeadline();
        module = taskToCopy.getModule();
        description = taskToCopy.getDescription();
        workload = taskToCopy.getWorkload();
        doneStatus = taskToCopy.getDoneStatus();
        recurrence = taskToCopy.getRecurrenceWrapper();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Activate the {@code StartTime} of the {@code Task} that we are building.
     */
    public TaskBuilder activateStartTime() {
        this.startTime = new OptionalField<>(new Time(this.deadline.time.minusHours(1)
                .format(DATE_TIME_FORMATTER_WITH_TIME)));
        return this;
    }

    /**
     * Activate the {@code StartTime} of the {@code Task} that we are building.
     *
     * @param validTime a validTime that before the deadline.
     * @return a TaskBuilder with startTime field.
     */
    public TaskBuilder activateStartTime(String validTime) {
        this.startTime = new OptionalField<>(new Time(validTime));
        return this;
    }

    /**
     * Deactivate the {@code StartTime} of the {@code Task} that we are building.
     */
    public TaskBuilder deactivateStartTime() {
        this.startTime = new OptionalField<>(null);
        return this;
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
     * Sets the {@code StartTime} of the {@code Task} that we are building.
     */
    public TaskBuilder withStartTime(String startTime) {
        if (startTime.equals("")) {
            this.startTime = new OptionalField<>(null);
        } else {
            this.startTime = new OptionalField<>(new Time(startTime));
        }
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

    /**
     * Sets the {@code recurrence} of the {@code Task} that we are building.
     */
    public TaskBuilder withRecurrence(String recurrence) {
        if (recurrence.equals("")) {
            this.recurrence = new OptionalField<>(null);
        } else {
            this.recurrence = new OptionalField<>(new Recurrence(recurrence));
        }
        return this;
    }

    /**
     * Build and returns a Task object.
     *
     * @return new Task object.
     */
    public Task build() {
        return new Task(name, startTime, deadline, module, description, workload, doneStatus, recurrence, tags);
    }
}
