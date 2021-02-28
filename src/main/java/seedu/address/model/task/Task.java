package seedu.address.model.task;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.Priority;
import seedu.address.model.common.Name;
import seedu.address.model.common.Category;
import seedu.address.model.common.Tag;

import java.util.HashSet;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a task in SOChedule.
 */
public class Task {
    // Fields
    private final Name name;
    private final Deadline deadline;
    private final Priority priority;
    private final Category category;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Name field must be present and not null.
     */
    public Task(Name name, Deadline deadline, Priority priority, Category category, Set<Tag> tags) {
        requireAllNonNull(name);
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.category = category;
        this.tags.addAll(tags);
    }
}


