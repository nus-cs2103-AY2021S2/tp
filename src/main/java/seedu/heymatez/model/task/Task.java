package seedu.heymatez.model.task;

import static seedu.heymatez.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.heymatez.model.assignee.Assignee;

/**
 * Represents a Task in HEY MATEz.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    // Identity fields
    private final Title title;

    // Data fields
    private final Description description;
    private final TaskStatus taskStatus;
    private final Deadline deadline;
    private final Priority priority;

    private final Set<Assignee> assignees = new HashSet<>();

    /**
     * Overloaded constructor which sets taskStatus to uncompleted and PRIORITY to unassigned by default.
     */
    public Task(Title title, Description description, Deadline deadline) {
        requireAllNonNull(title, description, deadline);
        this.title = title;
        this.description = description;
        this.taskStatus = TaskStatus.UNCOMPLETED;
        this.deadline = deadline;
        this.priority = Priority.UNASSIGNED;
    }

    /**
     * Overloaded constructor where every field must be present and not null. Priority here is default to unassigned.
     */
    public Task(Title title, Description description, Deadline deadline, TaskStatus taskStatus) {
        requireAllNonNull(title, description, deadline, taskStatus);
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
        this.priority = Priority.UNASSIGNED;
    }

    /**
     * Overloaded constructor where every field must be present and not null.
     * Overloaded constructor where every field must be present and not null. Status set to default of UNCOMPLETED
     */
    public Task(Title title, Description description, Deadline deadline, TaskStatus taskStatus, Priority priority) {
        requireAllNonNull(title, description, deadline, taskStatus, priority);
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
        this.priority = priority;
    }

    /**
     * Overloaded constructor where every field must be present and not null
     */
    public Task(Title title, Description description, Deadline deadline, TaskStatus taskStatus,
                Priority priority, Set<Assignee> assignees) {
        requireAllNonNull(title, description, deadline, taskStatus, priority);
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
        this.priority = priority;
        this.assignees.addAll(assignees);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public Set<Assignee> getAssignees() {
        return Collections.unmodifiableSet(assignees);
    }

    public boolean hasAssignee(Assignee assignee) {
        return assignees.contains(assignee);
    }

    public boolean hasAnyAssignees() {
        if (assignees.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Returns an updated task with the assignee specified removed from the set of
     * assignees to this task.
     *
     * @param assignee that is about to be removed.
     * @return Task that is updated without the specified assignee.
     */
    public Task removeAssignee(Assignee assignee) {
        Set<Assignee> copyAssignees = new HashSet<>();
        copyAssignees.addAll(this.assignees);
        copyAssignees.remove(assignee);

        return new Task(title, description, deadline, taskStatus, priority, copyAssignees);
    }

    /**
     * Returns an updated task with {@code assignee} edited from the set of assignees to this task.
     *
     * @param targetAssignee Assignee that is to be edited.
     * @param editedAssignee Edited assignee.
     * @return Task that is updated with the edited assignee.
     */
    public Task editAssignee(Assignee targetAssignee, Assignee editedAssignee) {
        Set<Assignee> newAssignees = new HashSet<>(this.assignees);
        newAssignees.remove(targetAssignee);
        newAssignees.add(editedAssignee);

        return new Task(title, description, deadline, taskStatus, priority, newAssignees);
    }

    public boolean isUnassigned() {
        return assignees.isEmpty();
    }

    public boolean isUncompleted() {
        return this.taskStatus.equals(TaskStatus.UNCOMPLETED);
    }

    /**
     * Returns true if both tasks have the same title.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTitle().equals(getTitle())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Task Status: ")
                .append(getTaskStatus())
                .append("; Priority: ")
                .append(getPriority());

        Set<Assignee> assignees = getAssignees();
        if (!assignees.isEmpty()) {
            builder.append("; Assignees: ");
            assignees.forEach(builder::append);
        }

        return builder.toString();
    }
}
