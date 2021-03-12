package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.person.Person;

/**
 * An Immutable ResidenceTracker that is serializable to JSON format.
 */
@JsonRootName(value = "residencetracker")
class JsonSerializableResidenceTracker {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableResidenceTracker} with the given persons.
     */
    @JsonCreator
    public JsonSerializableResidenceTracker(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyResidenceTracker} into this class for Json use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableResidenceTracker}.
     */
    public JsonSerializableResidenceTracker(ReadOnlyResidenceTracker source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ResidenceTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ResidenceTracker toModelType() throws IllegalValueException {
        ResidenceTracker residenceTracker = new ResidenceTracker();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (residenceTracker.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            residenceTracker.addPerson(person);
        }
        return residenceTracker;
    }

}
