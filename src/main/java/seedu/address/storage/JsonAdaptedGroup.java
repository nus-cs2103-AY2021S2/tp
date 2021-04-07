package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_DESERIALIZE_ERROR_DUMP_DATA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    public static final String MESSAGE_INVALID_PERSON = "Groups list contains non-existing person(s).";

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedGroup.class);

    private final String groupName;
    private final List<String> personNames = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("name") String groupName,
            @JsonProperty("persons")List<String> personNames) {
        this.groupName = groupName;

        if (personNames != null) {
            this.personNames.addAll(personNames);
        }
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        groupName = source.getName().fullName;
        personNames.addAll(source.getPersonNames().stream()
                .map(Name::toString)
                .collect(Collectors.toList()));
    }

    private IllegalValueException internalIllegalValueException(String message) {
        logger.warning(String.format(MESSAGE_DESERIALIZE_ERROR_DUMP_DATA, "Group"));
        logger.warning(this.toString());
        return new IllegalValueException(message);
    }

    private Name deserializeName(String name) throws IllegalValueException {
        if (name == null) {
            throw internalIllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw internalIllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        return new Name(trimmedName);
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     * Requires list of person to construct group list.
     * @param personList actual personList serialised from json file.
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType(List<Person> personList) throws IllegalValueException {
        final Name modelName = deserializeName(groupName);

        final Set<Name> modelPersonNameSet = new HashSet<>();
        for (String personName : personNames) {
            final Name modelPersonName = deserializeName(personName);

            Optional<Person> optionalPerson;
            // Go through Name#equals method to ignore case
            optionalPerson = personList.stream().filter(x -> x.getName().equals(modelPersonName)).findFirst();
            if (optionalPerson.isEmpty()) {
                throw internalIllegalValueException(MESSAGE_INVALID_PERSON);
            }
            modelPersonNameSet.add(optionalPerson.get().getName());
        }
        return new Group(modelName, modelPersonNameSet);
    }

    @Override
    public String toString() {
        return "JsonAdaptedGroup{"
                + "groupName='" + groupName + '\''
                + "personNames='" + personNames.toString() + '\''
                + '}';
    }
}
