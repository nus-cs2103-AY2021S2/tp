package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyWardrobe;
import seedu.address.model.Wardrobe;
import seedu.address.model.person.Person;

/**
 * An Immutable Wardrobe that is serializable to JSON format.
 */
@JsonRootName(value = "wardrobe")
class JsonSerializableWardrobe {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableWardrobe} with the given persons.
     */
    @JsonCreator
    public JsonSerializableWardrobe(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyWardrobe} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableWardrobe}.
     */
    public JsonSerializableWardrobe(ReadOnlyWardrobe source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Wardrobe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Wardrobe toModelType() throws IllegalValueException {
        Wardrobe wardrobe = new Wardrobe();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (wardrobe.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            wardrobe.addPerson(person);
        }
        return wardrobe;
    }

}
