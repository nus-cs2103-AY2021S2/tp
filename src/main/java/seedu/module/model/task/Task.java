package seedu.module.model.task;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static seedu.module.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.module.commons.core.optionalfield.OptionalField;
import seedu.module.model.tag.Tag;

/**
 * Represents a Task in the module book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    public static final String INVALID_START_TIME = "Start time have to be earlier than end time";

    // Identity fields
    private final Name name;
    private final OptionalField<Time> startTime;
    private final Time deadline;
    private final Module module;

    // Data fields
    private final Description description;
    private final Workload workload;
    private final DoneStatus doneStatus;
    private final OptionalField<Recurrence> recurrence;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, OptionalField<Time> startTime, Time deadline, Module module, Description description,
                Workload workload, DoneStatus doneStatus, OptionalField<Recurrence> recurrence, Set<Tag> tags) {
        requireAllNonNull(name, deadline, module, description, workload, doneStatus, tags);
        this.name = name;
        this.startTime = startTime;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        this.recurrence = recurrence;
        this.tags.addAll(tags);
    }

    /**
     * Factory method designed for done and notDone command, to get rid of repeat code.
     *
     * @param status done or not done status.
     * @return an edited task object.
     */
    public static Task setDoneStatus(Task task, DoneStatus status) {
        return new Task(task.name, task.startTime, task.deadline, task.module, task.description, task.workload,
                status, task.recurrence, task.tags);
    }

    /**
     * Factory method designed for tag and deleteTag command, to get rid of repeat code.
     *
     * @param tags new tags.
     * @return an edited task object.
     */
    public static Task setTags(Task task, Set<Tag> tags) {
        return new Task(task.name, task.startTime, task.deadline, task.module, task.description, task.workload,
                task.doneStatus, task.recurrence, tags);
    }

    /**
     * Factory method designed for tag and deleteTag command, to get rid of repeat code.
     *
     * @param recurrence new recurrence.
     * @return an edited task object.
     */
    public static Task setRecurrence(Task task, OptionalField<Recurrence> recurrence) {
        return new Task(task.name, task.startTime, task.deadline, task.module, task.description, task.workload,
                task.doneStatus, recurrence, task.tags);
    }

    /**
     * Method for updating recurrence task.
     *
     * @param task the task need to be updated.
     * @return the updated task.
     */
    public static Task updateRecurrenceTask(Task task) {
        DoneStatus defaultDoneStatus = task.getDoneStatus();

        boolean isUnchanged;
        Time newDeadline = getRecurringTime(task, task.getDeadline());
        OptionalField<Time> newStartTime = task.getStartTimeWrapper();

        isUnchanged = newDeadline.equals(task.getDeadline());

        if (!isUnchanged) {
            defaultDoneStatus = new DoneStatus(false);
            newStartTime = getRecurringTime(task, task.getStartTimeWrapper());
        }

        return new Task(task.name, newStartTime, newDeadline, task.module, task.description, task.workload,
                defaultDoneStatus, task.recurrence, task.tags);
    }

    public Name getName() {
        return name;
    }

    public Time getStartTime() {
        return startTime.getField();
    }

    public OptionalField<Time> getStartTimeWrapper() {
        return this.startTime;
    }

    public Time getDeadline() {
        return deadline;
    }

    public Module getModule() {
        return module;
    }

    public Description getDescription() {
        return description;
    }

    public Workload getWorkload() {
        return workload;
    }

    public DoneStatus getDoneStatus() {
        return doneStatus;
    }

    public Recurrence getRecurrence() {
        return recurrence.getField();
    }

    public OptionalField<Recurrence> getRecurrenceWrapper() {
        return this.recurrence;
    }

    public boolean isRecurring() {
        return !recurrence.isNull();
    }

    public boolean isDeadline() {
        return startTime.isNull();
    }

    /**
     * Check the validity of the relationship between startTime and deadLine
     *
     * @return if the relationship between startTime and deadLine is invalid
     */
    public boolean isTimeInvalid() {
        if (this.startTime.isNull()) {
            return false;
        }
        return this.startTime.getField().compareTo(this.deadline) >= 0;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tasks have the same name and same module code.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && otherTask.getModule().equals(getModule());
    }

    /**
     * Returns a new valid Deadline for the recurring task if previous recurring deadline has expired.
     *
     * @param task the task need to be updated.
     * @param oldTime current deadline of the recurring task.
     * @return new Deadline object for the recurring task.
     */
    private static Time getRecurringTime(Task task, Time oldTime) {
        requireAllNonNull(oldTime);
        assert(task.isRecurring());

        DateTimeFormatter formatter;
        String nextRecurringDeadlineStr = oldTime.value;
        Time currTime = Time.makeDeadlineWithTime(LocalDateTime.now());
        Recurrence taskRecurrence = task.getRecurrence();

        String dateValue = oldTime.value.split(" ")[0];
        if (oldTime.value.length() == dateValue.length()) {
            formatter = ISO_LOCAL_DATE;
        } else {
            formatter = Time.DATE_TIME_FORMATTER_WITH_TIME;
        }

        if (oldTime.compareTo(currTime) < 0) {
            // deadline is expired
            switch (taskRecurrence.getRecurrenceType()) {
            case daily:
                //change date to day + 1
                nextRecurringDeadlineStr = oldTime.getTime().plusDays(1)
                        .format(formatter);
                break;
            case weekly:
                //change date to day + 7
                nextRecurringDeadlineStr = oldTime.getTime().plusDays(7)
                        .format(formatter);
                break;
            case monthly:
                //change date to month + 1
                nextRecurringDeadlineStr = oldTime.getTime().plusMonths(1)
                        .format(formatter);
                break;
            default:
                // throw new CommandException(MESSAGE_INVALID_RECURRENCE);
            }
            return new Time(nextRecurringDeadlineStr);
        } else {
            //deadline is still valid
            return oldTime;
        }
    }

    /**
     * Overloaded getRecurringTime method for optional time.
     *
     * @param task the task need to be updated.
     * @param oldTime current deadline of the recurring task
     * @return new Deadline object for the recurring task.
     */
    private static OptionalField<Time> getRecurringTime(Task task, OptionalField<Time> oldTime) {
        if (oldTime.isNull()) {
            return oldTime;
        } else {
            Time newTime = Task.getRecurringTime(task, oldTime.getField());
            return new OptionalField<>(newTime);
        }
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

        return otherTask.getName().equals(getName())
                && otherTask.getStartTimeWrapper().equals(getStartTimeWrapper())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getModule().equals(getModule())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getWorkload().equals(getWorkload())
                && otherTask.getDoneStatus().equals(getDoneStatus())
                && otherTask.getRecurrenceWrapper().equals(getRecurrenceWrapper())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, deadline, module, description, workload, doneStatus, recurrence, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName());

        if (isDeadline()) {
            builder.append("; Deadline: ")
                    .append(getDeadline());
        } else {
            builder.append("; Start: ")
                    .append(getStartTime())
                    .append("; End: ")
                    .append(getDeadline());
        }
        builder.append("; Module: ")
                .append(getModule())
                .append("; Description: ")
                .append(getDescription())
                .append("; Workload: ")
                .append(getWorkload())
                .append("; Completion Status: ")
                .append(getDoneStatus());

        if (isRecurring()) {
            builder.append("; Recurring: ").append(getRecurrence());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    /**
     * Comparator of tasks using deadline as reference
     */
    public static class DeadlineComparator implements Comparator<Task> {

        @Override
        public String toString() {
            return "deadline";
        }

        /**
         * Method to compare the time field of two task. Use startTime, if present.
         *
         * @param t1 task 1
         * @param t2 task 2
         * @return the result of comparison
         */
        @Override
        public int compare(Task t1, Task t2) {
            if (t1.isDeadline() && t2.isDeadline()) {
                return t1.getDeadline().compareTo(t2.getDeadline());
            } else if (t1.isDeadline()) {
                return t1.getDeadline().compareTo(t2.getStartTime());
            } else if (t2.isDeadline()) {
                return t1.getStartTime().compareTo(t2.getDeadline());
            } else {
                return t1.getStartTime().compareTo(t2.getStartTime());
            }
        }
    }

    /**
     * Comparator of tasks using workload as reference
     */
    public static class WorkloadComparator implements Comparator<Task> {

        @Override
        public String toString() {
            return "workload";
        }

        @Override
        public int compare(Task t1, Task t2) {
            return t1.getWorkload().compareTo(t2.getWorkload());
        }
    }

    /**
     * Comparator of tasks using module as reference
     */
    public static class ModuleComparator implements Comparator<Task> {

        @Override
        public String toString() {
            return "module";
        }

        @Override
        public int compare(Task t1, Task t2) {
            return t1.getModule().compareTo(t2.getModule());
        }
    }

    /**
     * Comparator of tasks using name as reference
     */
    public static class NameComparator implements Comparator<Task> {

        @Override
        public String toString() {
            return "task name";
        }

        @Override
        public int compare(Task t1, Task t2) {
            return t1.getName().compareTo(t2.getName());
        }
    }

    /**
     * Comparator of tasks using description as reference
     */
    public static class DescriptionComparator implements Comparator<Task> {

        @Override
        public String toString() {
            return "description";
        }

        @Override
        public int compare(Task t1, Task t2) {
            return t1.getDescription().compareTo(t2.getDescription());
        }
    }

    /**
     * Comparator of tasks using number of tags as reference
     */
    public static class TagComparator implements Comparator<Task> {

        @Override
        public String toString() {
            return "number of tags";
        }

        @Override
        public int compare(Task t1, Task t2) {
            return t2.getTags().size() - t1.getTags().size();
        }
    }
}
