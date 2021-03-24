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
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableContactsList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableContactsList(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableContactsList(ReadOnlyContactsList source) {
        persons.addAll(source.getContactList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this dictionote book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ContactsList toModelType() throws IllegalValueException {
        ContactsList contactsList = new ContactsList();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Contact contact = jsonAdaptedPerson.toModelType();
            if (contactsList.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            contactsList.addContact(contact);
        }
        return contactsList;
    }

}
