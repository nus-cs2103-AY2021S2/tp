package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.common.Date;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 * A task is considered unique by comparing using {@code Task#isSameTask(Task)}. As such, adding and updating of
 * Tasks uses Task#isSameTask(Task) for equality so as to ensure that the task being added or updated is
 * unique in terms of identity in the UniqueTaskList. However, the removal of a task uses Task#equals(Object) so
 * as to ensure that the task with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#isSameTask(Task)
 */
public class UniqueTaskList implements Iterable<Task> {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private TaskComparator taskComparator = new TaskComparator();

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editedTask);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    public void setTasks(UniqueTaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(tasks);
    }

    /**
     * Sorts the contents of this list given {@code comparingVar}.
     * {@code comparingVar} must be a valid parameter.
     *
     * @param comparingVar The value to be used for sorting.
     */
    public void sort(String comparingVar) {
        taskComparator.setComparingVar(comparingVar);
        FXCollections.sort(internalList, taskComparator);
    }

    /**
     * Sorts the contents of this list given using current {@code comparingVar}.
     */
    public void sortDefault() {
        FXCollections.sort(internalList, taskComparator);
    }

    /**
     * Returns the number of completed tasks.
     */
    public int getNumCompletedTask() {
        int numCompletedTask = 0;
        int numTotalTask = internalList.size();
        for (int i = 0; i < numTotalTask; i++) {
            Task currTask = internalList.get(i);
            if (currTask.isComplete()) {
                numCompletedTask++;
            }
        }
        return numCompletedTask;
    }

    /**
     * Returns the number of overdue tasks.
     */
    public int getNumOverdueTask() {
        int numOverdueTask = 0;
        LocalDate now = LocalDate.now();
        String nowStr = now.format(DATE_FORMATTER);
        int numTotalTask = internalList.size();
        for (int i = 0; i < numTotalTask; i++) {
            Task currTask = internalList.get(i);
            if ((!currTask.isComplete())
                    && currTask.getDeadline().compareTo(new Date(nowStr)) < 0) {
                numOverdueTask++;
            }
        }
        return numOverdueTask;
    }

    /**
     * Returns the number of incompleted tasks before deadline.
     */
    public int getNumIncompleteTask() {
        int numIncompleteTask = 0;
        LocalDate now = LocalDate.now();
        String nowStr = now.format(DATE_FORMATTER);
        int numTotalTask = internalList.size();
        for (int i = 0; i < numTotalTask; i++) {
            Task currTask = internalList.get(i);
            if ((!currTask.isComplete())
                    && currTask.getDeadline().compareTo(new Date(nowStr)) >= 0) {
                numIncompleteTask++;
            }
        }
        return numIncompleteTask;
    }

    /**
     * Clears expired tasks (deadline past).
     */
    public void clearExpired() {
        internalList.removeIf(task -> !task.isDeadlineBeforeNow());
    }

    /**
     * Clears completed tasks.
     */
    public void clearCompleted() {
        internalList.removeIf(Task::isComplete);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                && internalList.equals(((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     */
    private boolean tasksAreUnique(List<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).isSameTask(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
