package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameTask comparison and tag equality)
 */
public class Planner implements ReadOnlyPlanner {

    private final UniqueTaskList tasks;
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTaskList();
        tags = new UniqueTagList();
    }

    public Planner() {
    }

    /**
     * Creates an Planner using the Tasks in the {@code toBeCopied}
     */
    public Planner(ReadOnlyPlanner toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Replaces the contents of the tag list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Resets the existing data of this {@code Planner} with {@code newData}.
     */
    public void resetData(ReadOnlyPlanner newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
        setTags(newData.getTagList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the planner.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Returns true if the deadline of a task is already over.
     */
    public boolean dateOver(Task task) {
        requireNonNull(task);
        return task.dateOver();
    }

    /**
     * Adds a task to the planner.
     * The task must not already exist in the planner.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the planner.
     * The task identity of {@code editedTask} must not be the same as another existing task in the planner.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code meetings} must not contain duplicate tasks.
     */
    public void setTask(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Removes {@code key} from this {@code Planner}.
     * {@code key} must exist in the planner.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    /**
     * Returns number of days left until the deadline of {@code Task}.
     * Task must already exist in the planner.
     *
     * @return a {code String} representing the number of days left until the deadline of the task.
     */
    public String countdown(Task task) {
        requireNonNull(task);

        long numberOfDaysLeft = tasks.countdown(task);

        return Long.toString(numberOfDaysLeft);
    }

    //// tag-level operations

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the planner.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds a tag to the planner.
     * The tag must not already exist in the planner.
     */
    public void addTag(Tag p) {
        tags.add(p);
    }

    /**
     * Adds tags that are not in the planner from the set of tags and returns a set containing those originally in the
     * planner and new tags that are now added.
     */
    public Set<Tag> addTagsIfAbsent(Set<Tag> tags) {
        Set<Tag> uniqueTags = new HashSet<>();
        tags.forEach(tag -> {
            if (hasTag(tag)) {
                uniqueTags.add(getTag(tag));
            } else {
                uniqueTags.add(tag);
                addTag(tag);
            }
        });
        return uniqueTags;
    }

    /**
     * Gets a tag from the planner.
     * The tag must already exist in the planner.
     */
    public Tag getTag(Tag p) {
        return tags.get(p);
    }

    /**
     * Replaces the given set of tags {@code target} in the list with {@code editedTags}.
     * {@code target} must exist in the planner.
     * The tag identities of {@code editedTags} must not be the same as other existing tags in the planner.
     */
    public void setTags(Set<Tag> target, Set<Tag> editedTag) {
        requireNonNull(editedTag);

        tags.setTags(target, editedTag);
    }

    /**
     * Removes {@code key} from this {@code Planner}.
     * {@code key} must exist in the planner.
     */
    public void removeTag(Tag key) {
        tags.remove(key);
    }

    /**
     * Returns the comparator that specifies how Tags are being sorted in the TagList.
     *
     * @return Comparator that is used to sort the Tags.
     */
    public Comparator<Tag> getTagComparator() {
        return tags.getTagComparator();
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks; "
                + tags.asUnmodifiableObservableList().size() + " tags;";
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        tasks.forEach(task -> {
            addTagsIfAbsent(task.getTags());
        });
        return tags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Planner // instanceof handles nulls
                && tasks.equals(((Planner) other).tasks)
                && tags.equals(((Planner) other).tags));
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks, tags);
    }
}
