package seedu.address.testutil;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;
import seedu.address.model.task.Title;

public class TaskBuilder {
    public static final String DEFAULT_TITLE = "Book venue";
    public static final String DEFAULT_DESCRIPTION = "Book venue for cca dinner";
    public static final String DEFAULT_DEADLINE = "2021-04-04";
    public static final String DEFAULT_STATUS = "UNCOMPLETED";
    public static final String DEFAULT_PRIORITY = "UNASSIGNED";

    public static final String COMPLETED_STATUS = "UNCOMPLETED";
    public static final String HIGH_PRIORITY = "HIGH";
    public static final String MEDIUM_PRIORITY = "MEDIUM";
    public static final String LOW_PRIORITY = "LOW";

    private Title title;
    private Description description;
    private Deadline deadline;
    private TaskStatus status;
    private Priority priority;


    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        deadline = new Deadline(DEFAULT_DEADLINE);
        status = TaskStatus.valueOf(DEFAULT_STATUS);
        priority = Priority.valueOf(DEFAULT_PRIORITY);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code TaskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        description = taskToCopy.getDescription();
        deadline = taskToCopy.getDeadline();
        status = taskToCopy.getTaskStatus();
        priority = taskToCopy.getPriority();
    }

    /**
     * Sets the {@code Title} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = new Title(title);
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
     * Sets the {@code TaskStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskStatus(String status) {
        this.status = TaskStatus.valueOf(status.toUpperCase());
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withPriority(String priority) {
        this.priority = Priority.valueOf(priority.toUpperCase());
        return this;
    }

    public Task build() {
        return new Task(title, description, deadline, status, priority);
    }

}
