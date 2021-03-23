package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Duration;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TITLE = "Amy Bee";
    public static final String DEFAULT_DEADLINE = "08/08/2021";
    public static final String DEFAULT_DURATION = "15:30";
    public static final String DEFAULT_RECURRINGSCHEDULE = "[10/06/2021][Mon][weekly]";
    public static final String DEFAULT_DESCRIPTION = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STATUS = "not done";

    private Title title;
    private Deadline deadline;
    private Duration duration;
    private RecurringSchedule recurringSchedule;
    private Description description;
    private Status status;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = new Title(DEFAULT_TITLE);
        deadline = new Deadline(DEFAULT_DEADLINE);
        duration = new Duration(DEFAULT_DURATION);
        recurringSchedule = new RecurringSchedule(DEFAULT_RECURRINGSCHEDULE);
        description = new Description(DEFAULT_DESCRIPTION);
        status = new Status(DEFAULT_STATUS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        deadline = taskToCopy.getDeadline();
        duration = taskToCopy.getDuration();
        recurringSchedule = taskToCopy.getRecurringSchedule();
        description = taskToCopy.getDescription();
        status = taskToCopy.getStatus();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = new Title(title);
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
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Task} that we are building.
     */
    public TaskBuilder withDuration(String duration) {
        this.duration = new Duration(duration);
        return this;
    }

    /**
     * Sets the {@code RecurringSchedule} of the {@code Task} that we are building.
     */
    public TaskBuilder withRecurringSchedule(String recurringSchedule) {
        this.recurringSchedule = new RecurringSchedule(recurringSchedule);
        return this;
    }

    public Task build() {
        return new Task(title, deadline, duration, recurringSchedule, description, status, tags);
    }

}
