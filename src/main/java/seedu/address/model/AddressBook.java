package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

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

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the plan list with {@code plans}.
     * {@code plans} must not contain duplicate plans.
     */
    public void setPersons(List<Plan> plans) {
        this.persons.setPersons(plans);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// plan-level operations

    /**
     * Returns true if a plan with the same identity as {@code plan} exists in the address book.
     */
    public boolean hasPerson(Plan plan) {
        requireNonNull(plan);
        return persons.contains(plan);
    }

    /**
     * Adds a plan to the address book.
     * The plan must not already exist in the address book.
     */
    public void addPerson(Plan p) {
        persons.add(p);
    }

    /**
     * Replaces the given plan {@code target} in the list with {@code editedPlan}.
     * {@code target} must exist in the address book.
     * The plan identity of {@code editedPlan} must not be the same as another existing plan in the address book.
     */
    public void setPerson(Plan target, Plan editedPlan) {
        requireNonNull(editedPlan);

        persons.setPerson(target, editedPlan);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Plan key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Plan> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
