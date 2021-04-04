package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    public static final String MESSAGE_INVALID_PERSON = "Groups list contains non-existing person(s).";

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
            personNames.addAll(personNames);
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

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     * Requires list of person to construct group list.
     * @param personList actual personList serialised from json file.
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType(List<Person> personList) throws IllegalValueException {
        if (groupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(groupName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(groupName);

        final Set<Name> modelPersonNameSet = new HashSet<>();
        for (String personName : personNames) {
            if (!Name.isValidName(personName)) {
                throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
            }
            Person person;
            person = personList.stream().filter(x->x.getName()
                    .toString().equals(personName))
                    .findFirst().get();
            if (person == null) {
                throw new IllegalValueException(MESSAGE_INVALID_PERSON);
            }
            modelPersonNameSet.add(person.getName());
        }
        return new Group(modelName, modelPersonNameSet);
    }
}
