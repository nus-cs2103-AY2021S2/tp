package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

public class TaskBuilder {
    private static final String DEFAULT_NAME = "Sample Task";
    private static final String DEFAULT_DEADLINE = "2022-01-01";
    private static final String DEFAULT_PRIORITY = "0";

    private Name name;
    private Date deadline;
    private Priority priority;
    private final Set<Category> categories;
    private final Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        deadline = new Date(DEFAULT_DEADLINE);
        priority = new Priority(DEFAULT_PRIORITY);
        categories = new HashSet<>();
        tags = new HashSet<>();

    }

    public Task build() {
        return new Task(name, deadline, priority, categories, tags);
    }

}
