package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineTime;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Status;
import seedu.address.model.person.Task;
import seedu.address.model.person.TaskName;
import seedu.address.model.person.Weightage;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Weekly Quiz 11";
    public static final String DEFAULT_CODE = "CS2103";
    public static final String DEFAULT_DATE = "10-10-2022";
    public static final String DEFAULT_TIME = "10:10";
    public static final String DEFAULT_STATUS = "Unfinished";
    public static final Integer DEFAULT_WEIGHTAGE = 0;
    public static final String DEFAULT_REMARK = "";
    public static final String DEFAULT_PRIORITY_TAG = "LOW";

    private TaskName taskName;
    private ModuleCode moduleCode;
    private DeadlineDate deadlineDate;
    private DeadlineTime deadlineTime;
    private Status status;
    private Weightage weightage;
    private Notes notes;
    private Set<Tag> tags;
    private PriorityTag priorityTag;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = new TaskName(DEFAULT_NAME);
        moduleCode = new ModuleCode(DEFAULT_CODE);
        deadlineDate = new DeadlineDate(DEFAULT_DATE);
        deadlineTime = new DeadlineTime(DEFAULT_TIME);
        status = new Status(DEFAULT_STATUS);
        weightage = new Weightage(DEFAULT_WEIGHTAGE);
        notes = new Notes(DEFAULT_REMARK);
        tags = new HashSet<>();
        priorityTag = new PriorityTag(DEFAULT_PRIORITY_TAG);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getTaskName();
        moduleCode = taskToCopy.getModuleCode();
        deadlineDate = taskToCopy.getDeadlineDate();
        deadlineTime = taskToCopy.getDeadlineTime();
        status = taskToCopy.getStatus();
        weightage = taskToCopy.getWeightage();
        notes = taskToCopy.getNotes();
        tags = new HashSet<>(taskToCopy.getTags());
        priorityTag = taskToCopy.getPriorityTag();
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.taskName = new TaskName(name);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Task} that we are building.
     */
    public TaskBuilder withCode(String code) {
        this.moduleCode = new ModuleCode(code);
        return this;
    }

    /**
     * Sets the {@code DeadlineDate} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadlineDate(String deadlineDate) {
        this.deadlineDate = new DeadlineDate(deadlineDate);
        return this;
    }

    /**
     * Sets the {@code DeadlineTime} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadlineTime(String deadlineTime) {
        this.deadlineTime = new DeadlineTime(deadlineTime);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Weightage} of the {@code Task} that we are building.
     */
    public TaskBuilder withWeightage(Integer weightage) {
        // perhaps this shouldn't be an integer?
        this.weightage = new Weightage(weightage);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Notes} of the {@code Task} that we are building.
     */
    public TaskBuilder withNotes(String notes) {
        this.notes = new Notes(notes);
        return this;
    }

    /**
     * Sets the {@code PriorityTag} of the {@code Task} that we are building.
     */
    public TaskBuilder withPriorityTag(String priorityTag) {
        this.priorityTag = new PriorityTag(priorityTag);
        return this;
    }

    /**
     * Build a new Task with default attributes.
     */
    public Task build() {
        return new Task(taskName, moduleCode, deadlineDate,
            deadlineTime, status, weightage, notes, tags, priorityTag);
    }

}
