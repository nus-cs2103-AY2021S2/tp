package seedu.dictionote.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.contact.UniquePersonList;

/**
 * Wraps all data at the dictionote-book level
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
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Contact> contacts) {
        this.persons.setPersons(contacts);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getContactList());
    }

    //// person-level operations

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the contacts list.
     */
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return persons.contains(contact);
    }

    /**
     * Adds a person to the dictionote book.
     * The person must not already exist in the dictionote book.
     */
    public void addContact(Contact p) {
        persons.add(p);
    }
    /**
     * Adds a person to the dictionote book.
     * The person must not already exist in the dictionote book.
     */
    public void setPerson(Contact target, Contact editedContact) {
        requireNonNull(editedContact);
        persons.setPerson(target, editedContact);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the dictionote book.
     */
    public void removePerson(Contact key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Contact> getContactList() {
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
