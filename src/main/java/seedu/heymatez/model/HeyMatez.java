package seedu.heymatez.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.heymatez.model.person.Name;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.person.UniquePersonList;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.model.task.UniqueTaskList;

/**
 * Wraps all data at Hey Matez level.
 * Duplicates are not allowed (by .isSamePerson comparison).
 */
public class HeyMatez implements ReadOnlyHeyMatez {

    private final UniquePersonList persons;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tasks = new UniqueTaskList();
    }

    public HeyMatez() {}

    /**
     * Creates HeyMatez using the Persons in the {@code toBeCopied}.
     */
    public HeyMatez(ReadOnlyHeyMatez toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of {@code HeyMatez} with {@code newData}.
     */
    public void resetData(ReadOnlyHeyMatez newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in HEY MATEz.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to HEY MATEz.
     * The person must not already exist in HEY MATEz.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in HEY MATEz.
     * The person identity of {@code editedPerson} must not be the same as another existing person in HEY MATEz.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from HEY MATEz.
     * {@code key} must exist in HEY MATEz.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    /**
     * Replaces the contents of the task list with {@code tasks}.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Returns true if a task with the same identity as {@code task} exists in HEY MATEz.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to HEY MATEz.
     */
    public void addTask(Task t) {
        tasks.addTask(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in HEY MATEz.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code UniqueTaskList}.
     * {@code key} must exist in the task list.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    /**
     * Removes the Assignee with the given name from the Assignee sets of the current task list
     */
    public void removeAssignee(Name name) {
        tasks.removeAssignee(name);
    }

    /**
     * Replaces the given Assignee {@code targetName} with {@code editedName} in tasks from the task list.
     * {@code targetName} must exist in HEY MATEz.
     */
    public void editAssignee(Name targetName, Name editedName) {
        tasks.editAssignee(targetName, editedName);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HeyMatez // instanceof handles nulls
                && persons.equals(((HeyMatez) other).persons)
                && tasks.equals(((HeyMatez) other).tasks));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

}
