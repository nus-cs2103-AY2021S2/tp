package seedu.module.model.task;

import static seedu.module.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.module.model.tag.Tag;

/**
 * Represents a Task in the module book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    public static final String INVALID_START_TIME = "Start time have to be earlier than end time";

    // Identity fields
    private final Name name;
    private Time startTime;
    private final Time deadline;
    private final Module module;
    private boolean isRecurringTask;
    private final boolean isDeadline;

    // Data fields
    private final Description description;
    private final Workload workload;
    private final DoneStatus doneStatus;
    private final Set<Tag> tags = new HashSet<>();
    private Recurrence recurrence;

    /**
     * Every field must be present and not null.
     */
    //no starttime, no recurrence
    public Task(Name name, Time deadline, Module module, Description description,
                Workload workload, DoneStatus doneStatus, Set<Tag> tags) {
        requireAllNonNull(name, deadline, module, description, workload, doneStatus, tags);
        this.name = name;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        this.tags.addAll(tags);
        this.isRecurringTask = false;
        this.isDeadline = true;
    }

    /**
     * Overloaded constructor for Task when an optional Recurrence is passed in.
     * Every field must be present and not null.
     */
    //no startime, recurrence
    public Task(Name name, Time deadline, Module module, Description description,
                Workload workload, DoneStatus doneStatus, Recurrence recurrence, Set<Tag> tags) {
        requireAllNonNull(name, deadline, module, description, workload, doneStatus, recurrence, tags);
        this.name = name;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        this.recurrence = recurrence;
        this.tags.addAll(tags);
        this.isRecurringTask = true;
        this.isDeadline = true;
    }

    /**
     * Every field must be present and not null.
     */
    //starttime, no recurrence
    public Task(Name name, Time startTime, Time deadline, Module module, Description description,
                Workload workload, DoneStatus doneStatus, Set<Tag> tags) {
        requireAllNonNull(name, startTime, deadline, module, description, workload, doneStatus, tags);
        this.name = name;
        this.startTime = startTime;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        this.tags.addAll(tags);
        this.isRecurringTask = false;
        this.isDeadline = false;
    }

    /**
     * Overloaded constructor for Task when an optional Recurrence is passed in.
     * Every field must be present and not null.
     */
    //starttime, recurrence
    public Task(Name name, Time startTime, Time deadline, Module module, Description description,
                Workload workload, DoneStatus doneStatus, Recurrence recurrence, Set<Tag> tags) {
        requireAllNonNull(name, startTime, deadline, module, description, workload, doneStatus, tags);
        this.name = name;
        this.startTime = startTime;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        this.tags.addAll(tags);
        this.recurrence = recurrence;
        this.isRecurringTask = true;
        this.isDeadline = false;
    }

    public Name getName() {
        return name;
    }

    public Time getStartTime() {
        return startTime;
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
        return recurrence;
    }

    public boolean isRecurring() {
        return isRecurringTask;
    }

    public boolean isDeadline() {
        return isDeadline;
    }

    /**
     * Check the validity of the relationship between startTime and deadLine
     *
     * @return if the relationship between startTime and deadLine is invalid
     */
    public boolean isTimeInvalid() {
        if (this.startTime == null) {
            return false;
        }
        return this.startTime.compareTo(this.deadline) >= 0;
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
     * @param currDeadline current deadline of the recurring task
     * @param taskRecurrence recurrence of the task
     * @return new Deadline object for the recurring task.
     */
    public Time getRecurringDeadline(Time currDeadline, Recurrence taskRecurrence) {
        requireAllNonNull(currDeadline, taskRecurrence);
        assert(isRecurringTask);

        String nextRecurringDeadlineStr = currDeadline.value;
        Time currTime = Time.makeDeadlineWithTime(LocalDateTime.now());

        if (currDeadline.compareTo(currTime) < 0) {
            // deadline is expired
            switch (taskRecurrence.getRecurrenceType()) {
            case daily:
                //change date to day + 1
                nextRecurringDeadlineStr = currDeadline.getTime().plusDays(1)
                        .format(Time.DATE_TIME_FORMATTER_WITH_TIME);
                break;
            case weekly:
                //change date to day + 7
                nextRecurringDeadlineStr = currDeadline.getTime().plusDays(7)
                        .format(Time.DATE_TIME_FORMATTER_WITH_TIME);
                break;
            case monthly:
                //change date to month + 1
                nextRecurringDeadlineStr = currDeadline.getTime().plusMonths(1)
                        .format(Time.DATE_TIME_FORMATTER_WITH_TIME);
                break;
            default:
                // throw new CommandException(MESSAGE_INVALID_RECURRENCE);
            }
            return new Time(nextRecurringDeadlineStr);
        } else {
            //deadline is still valid
            return currDeadline;
        }
    }

    /**
     * Returns a new Task object if the task is recurring and the deadline has expired.
     *
     * @param newDeadline is the new Recurring Deadline.
     */
    public Task makeNewRecurringTask(Time newDeadline, Time newStartTime) {
        DoneStatus defaultDoneStatus = new DoneStatus(false);
        if (isDeadline) {
            return new Task(getName(), newDeadline, getModule(), getDescription(), getWorkload(), defaultDoneStatus,
                    getRecurrence(), getTags());
        } else {
            return new Task(getName(), newStartTime, newDeadline, getModule(), getDescription(), getWorkload(),
                    defaultDoneStatus, getRecurrence(), getTags());
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

        if (!isRecurring() && !isDeadline()) {
            return otherTask.getName().equals(getName())
                    && otherTask.getStartTime() == getStartTime()
                    && otherTask.getDeadline().equals(getDeadline())
                    && otherTask.getModule().equals(getModule())
                    && otherTask.getDescription().equals(getDescription())
                    && otherTask.getWorkload().equals(getWorkload())
                    && otherTask.getDoneStatus().equals(getDoneStatus())
                    && otherTask.isDeadline() == isDeadline()
                    && otherTask.isRecurring() == isRecurring()
                    && otherTask.getTags().equals(getTags());

        } else if (!isRecurring() && isDeadline()) {
            return otherTask.getName().equals(getName())
                    && otherTask.getDeadline().equals(getDeadline())
                    && otherTask.getModule().equals(getModule())
                    && otherTask.getDescription().equals(getDescription())
                    && otherTask.getWorkload().equals(getWorkload())
                    && otherTask.getDoneStatus().equals(getDoneStatus())
                    && otherTask.isDeadline() == isDeadline()
                    && otherTask.isRecurring() == isRecurring()
                    && otherTask.getTags().equals(getTags());

        } else if (isRecurring() && !isDeadline()) {
            return otherTask.getName().equals(getName())
                    && otherTask.getStartTime().equals(getStartTime())
                    && otherTask.getDeadline().equals(getDeadline())
                    && otherTask.getModule().equals(getModule())
                    && otherTask.getDescription().equals(getDescription())
                    && otherTask.getWorkload().equals(getWorkload())
                    && otherTask.getDoneStatus().equals(getDoneStatus())
                    && otherTask.getRecurrence().equals(getRecurrence())
                    && otherTask.isDeadline() == isDeadline()
                    && otherTask.isRecurring() == isRecurring()
                    && otherTask.getTags().equals(getTags());

        } else if (isRecurring() && isDeadline()) {
            return otherTask.getName().equals(getName())
                    && otherTask.getDeadline().equals(getDeadline())
                    && otherTask.getModule().equals(getModule())
                    && otherTask.getDescription().equals(getDescription())
                    && otherTask.getWorkload().equals(getWorkload())
                    && otherTask.getDoneStatus().equals(getDoneStatus())
                    && otherTask.getRecurrence().equals(getRecurrence())
                    && otherTask.isDeadline() == isDeadline()
                    && otherTask.isRecurring() == isRecurring()
                    && otherTask.getTags().equals(getTags());
        } else {
            return false;
        }
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
        if (isDeadline) {
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

        if (isRecurringTask) {
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
            if (t1.isDeadline && t2.isDeadline) {
                return t1.getDeadline().compareTo(t2.getDeadline());
            } else if (t1.isDeadline) {
                return t1.getDeadline().compareTo(t2.getStartTime());
            } else if (t2.isDeadline) {
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
