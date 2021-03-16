package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineTime;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleName;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Status;
import seedu.address.model.person.Task;
import seedu.address.model.person.Weightage;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Software Engineerings";
    public static final String DEFAULT_CODE = "CS2103";
    public static final String DEFAULT_DATE = "10-10-2020";
    public static final String DEFAULT_TIME = "10:10";
    public static final String DEFAULT_STATUS = "Unfinished";
    public static final Integer DEFAULT_WEIGHTAGE = 0;
    public static final String DEFAULT_REMARK = "";

    private ModuleName moduleName;
    private ModuleCode moduleCode;
    private DeadlineDate deadlineDate;
    private DeadlineTime deadlineTime;
    private Status status;
    private Weightage weightage;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        moduleName = new ModuleName(DEFAULT_NAME);
        moduleCode = new ModuleCode(DEFAULT_CODE);
        deadlineDate = new DeadlineDate(DEFAULT_DATE);
        deadlineTime = new DeadlineTime(DEFAULT_TIME);
        status = new Status(DEFAULT_STATUS);
        weightage = new Weightage(DEFAULT_WEIGHTAGE);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        moduleName = taskToCopy.getModuleName();
        moduleCode = taskToCopy.getModuleCode();
        deadlineDate = taskToCopy.getDeadlineDate();
        deadlineTime = taskToCopy.getDeadlineTime();
        status = taskToCopy.getStatus();
        weightage = taskToCopy.getWeightage();
        remark = taskToCopy.getRemark();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code ModuleName} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
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
     * Sets the {@code Remark} of the {@code Task} that we are building.
     */
    public TaskBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Build a new Task with default attributes.
     */
    public Task build() {
        return new Task(moduleName, moduleCode, deadlineDate,
                deadlineTime, status, weightage, remark, tags);
    }

}
