package fooddiary.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.model.FoodDiary;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.entry.Entry;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyFoodDiary source) {
        persons.addAll(source.getEntryList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FoodDiary toModelType() throws IllegalValueException {
        FoodDiary foodDiary = new FoodDiary();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Entry entry = jsonAdaptedPerson.toModelType();
            if (foodDiary.hasPerson(entry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            foodDiary.addPerson(entry);
        }
        return foodDiary;
    }

}
