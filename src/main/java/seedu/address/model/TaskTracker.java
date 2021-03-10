package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Task;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */

public class TaskTracker implements ReadOnlyTaskTracker {


    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public TaskTracker() {
    }

    /**
     * Creates an TaskTracker using the Persons in the {@code toBeCopied}
     */


    public TaskTracker(ReadOnlyTaskTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Task> tasks) {
        this.persons.setPersons(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskTracker newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Task task) {
        requireNonNull(task);
        return persons.contains(task);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Task p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Task target, Task editedTask) {
        requireNonNull(editedTask);

        persons.setPerson(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code TaskTracker}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Task key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTracker // instanceof handles nulls
                && persons.equals(((TaskTracker) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
