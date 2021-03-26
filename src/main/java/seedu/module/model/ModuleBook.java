package seedu.module.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.module.model.task.Task;
import seedu.module.model.task.UniqueTaskList;

/**
 * Wraps all data at the module-book level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class ModuleBook implements ReadOnlyModuleBook {

    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTaskList();
    }

    public ModuleBook() {
    }

    /**
     * Creates an ModuleBook using the Tasks in the {@code toBeCopied}
     */
    public ModuleBook(ReadOnlyModuleBook toBeCopied) {
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
     * Sorts the task list by deadline.
     * @param factor
     */
    public void sortTasks(Comparator<Task> factor) {
        this.tasks.sortTasks(factor);
    }

    /**
     * Resets the existing data of this {@code ModuleBook} with {@code newData}.
     */
    public void resetData(ReadOnlyModuleBook newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the module book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the module book.
     * The task must not already exist in the module book.
     */
    public void addTask(Task p) {
        assert(p != null);
        if (checkForSupportedModuleCode(p)) {
            assert(ModuleManager.moduleIsValid(p.getModule().toString()));
            ModuleManager.insertTaskToMapping(p.getModule(), p);
            tasks.add(p);
        } else {

        }
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the module book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the module book.
     */
    public void setTask(Task target, Task editedTask) {
        if (checkForSupportedModuleCode(editedTask)) {
            assert(ModuleManager.moduleIsValid(editedTask.getModule().toString()));
            ModuleManager.deleteTaskFromMapping(target.getModule(), target);
            ModuleManager.insertTaskToMapping(editedTask.getModule(), editedTask);
            tasks.setTask(target, editedTask);
        } else {

        }
    }

    /**
     * Removes {@code key} from this {@code ModuleBook}.
     * {@code key} must exist in the module book.
     */
    public void removeTask(Task p) {
        if (checkForSupportedModuleCode(p)) {
            assert(ModuleManager.moduleIsValid(p.getModule().toString()));
            ModuleManager.deleteTaskFromMapping(p.getModule(), p);
            tasks.remove(p);
        } else {

        }
    }

    /**
     * Extra check that task contains a supported module code.
     *
     * @param task
     * @return True if Module is supported
     */
    public boolean checkForSupportedModuleCode(Task task) {
        return ModuleManager.getListOfExistingModules().contains(task.getModule().toString());
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleBook // instanceof handles nulls
                && tasks.equals(((ModuleBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
