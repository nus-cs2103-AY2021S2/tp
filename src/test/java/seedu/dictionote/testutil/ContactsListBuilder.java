package seedu.dictionote.testutil;

import seedu.dictionote.model.ContactsList;
import seedu.dictionote.model.contact.Contact;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class ContactsListBuilder {

    private ContactsList contactsList;

    public ContactsListBuilder() {
        contactsList = new ContactsList();
    }

    public ContactsListBuilder(ContactsList contactsList) {
        this.contactsList = contactsList;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ContactsListBuilder withContact(Contact contact) {
        contactsList.addContact(contact);
        return this;
    }

    public ContactsList build() {
        return contactsList;
    }
}
