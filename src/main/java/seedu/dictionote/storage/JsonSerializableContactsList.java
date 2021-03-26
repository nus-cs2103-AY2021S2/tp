package seedu.dictionote.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.model.ContactsList;
import seedu.dictionote.model.ReadOnlyContactsList;
import seedu.dictionote.model.contact.Contact;

/**
 * An Immutable ContactsList that is serializable to JSON format.
 */
@JsonRootName(value = "contactslist")
class JsonSerializableContactsList {

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contacts list contains duplicate contacts(s).";

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableContactsList} with the given persons.
     */
    @JsonCreator
    public JsonSerializableContactsList(@JsonProperty("contacts") List<JsonAdaptedContact> contacts) {
        this.contacts.addAll(contacts);
    }

    /**
     * Converts a given {@code ReadOnlyContactsList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableContactsList}.
     */
    public JsonSerializableContactsList(ReadOnlyContactsList source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
    }

    /**
     * Converts this contacts list into the model's {@code ContactsList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ContactsList toModelType() throws IllegalValueException {
        ContactsList contactsList = new ContactsList();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (contactsList.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            contactsList.addContact(contact);
        }
        return contactsList;
    }

}
