package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.attributes.Description;
import seedu.address.model.task.attributes.Duration;
import seedu.address.model.task.attributes.RecurringSchedule;
import seedu.address.model.task.attributes.Status;
import seedu.address.model.task.attributes.Title;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TITLE = "Amy Bee";
    public static final String DEFAULT_DATE = "";
    public static final String DEFAULT_DURATION = "";
    public static final String DEFAULT_RECURRINGSCHEDULE = "";
    public static final String DEFAULT_DESCRIPTION = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STATUS = "not done";

    private Title title;
    private Date date;
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
        date = new Date(DEFAULT_DATE);
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
        date = taskToCopy.getDate();
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
    public TaskBuilder withTags(String... tags) {
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
     * Sets the {@code Date} of the {@code Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Task} that we are building.
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
        return new Task(title, date, duration, recurringSchedule, description, status, tags);
    }

}
