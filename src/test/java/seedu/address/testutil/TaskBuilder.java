package seedu.address.testutil;

import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

public class TaskBuilder {
    public static final String DEFAULT_TITLE = "Book venue";
    public static final String DEFAULT_DESCRIPTION = "Book venue for cca dinner";

    private Title title;
    private Description description;


    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code TaskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        description = taskToCopy.getDescription();
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

    public Task build() {
        return new Task(title, description);
    }

}
