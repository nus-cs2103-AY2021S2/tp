package seedu.dictionote.model;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.contact.UniqueContactList;
import seedu.dictionote.model.contact.exceptions.InvalidContactMailtoLinkException;

/**
 * Wraps all data at the dictionote-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ContactsList implements ReadOnlyContactsList {

    private final UniqueContactList contacts;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        contacts = new UniqueContactList();
    }

    public ContactsList() {}

    /**
     * Creates a ContactsList using the Persons in the {@code toBeCopied}
     */
    public ContactsList(ReadOnlyContactsList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts.setContacts(contacts);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyContactsList newData) {
        requireNonNull(newData);
        setContacts(newData.getContactList());
    }

    //// person-level operations

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the contacts list.
     */
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contacts.contains(contact);
    }

    /**
     * Adds a person to the dictionote book.
     * The person must not already exist in the dictionote book.
     */
    public void addContact(Contact p) {
        contacts.add(p);
    }
    /**
     * Adds a person to the dictionote book.
     * The person must not already exist in the dictionote book.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireNonNull(editedContact);
        contacts.setContact(target, editedContact);
    }

    /**
     * Invokes the user's OS email client to send a new email to the given contact.
     * {@code contact} must exist in the contacts list.
     */
    public void emailContact(Contact contact) throws InvalidContactMailtoLinkException {
        URI contactMailtoLink = URI.create("mailto:" + contact.getEmail());
        Desktop userDesktop = Desktop.getDesktop();

        // credit to TorstenH. and alexey_s from CodeProject for the URL invocation code.
        // link to the posts: https://www.codeproject.com/questions/398241/how-to-open-url-in-java
        try {
            userDesktop.mail(contactMailtoLink); // invoke user's OS default mail client.
        } catch (IOException e) {
            throw new InvalidContactMailtoLinkException();
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the dictionote book.
     */
    public void removeContact(Contact key) {
        contacts.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return contacts.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Contact> getContactList() {
        return contacts.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactsList // instanceof handles nulls
                && contacts.equals(((ContactsList) other).contacts));
    }

    @Override
    public int hashCode() {
        return contacts.hashCode();
    }
}
