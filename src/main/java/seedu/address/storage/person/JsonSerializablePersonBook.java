package seedu.address.storage.person;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonBook;

/**
 * An Immutable PersonBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
public class JsonSerializablePersonBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<Person> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePersonBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePersonBook(@JsonProperty("persons") List<Person> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyBook<Person>} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializablePersonBook(ReadOnlyBook<Person> source) {
        persons.addAll(source.getItemList());
    }

    /**
     * Converts this person book into the model's {@code PersonBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PersonBook toModelType() throws IllegalValueException {
        PersonBook personBook = new PersonBook();
        personBook.setPersons(persons);
        return personBook;
    }

}
