package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.groupmate.Name;
import seedu.address.model.groupmate.Role;

/**
 * Jackson-friendly version of {@link Groupmate}.
 */
class JsonAdaptedGroupmate {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Groupmate's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedRole> roles = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGroupmate} with the given groupmate details.
     */
    @JsonCreator
    public JsonAdaptedGroupmate(@JsonProperty("name") String name,
                                @JsonProperty("roles") List<JsonAdaptedRole> roles) {
        this.name = name;
        if (roles != null) {
            this.roles.addAll(roles);
        }
    }

    /**
     * Converts a given {@code Groupmate} into this class for Jackson use.
     */
    public JsonAdaptedGroupmate(Groupmate source) {
        name = source.getName().fullName;
        roles.addAll(source.getRoles().stream()
                .map(JsonAdaptedRole::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Groupmate object into the model's {@code Groupmate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted groupmate.
     */
    public Groupmate toModelType() throws IllegalValueException {
        final List<Role> groupmateRoles = new ArrayList<>();
        for (JsonAdaptedRole role : roles) {
            groupmateRoles.add(role.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Name modelName = new Name(name);

        final Set<Role> modelRoles = new HashSet<>(groupmateRoles);
        return new Groupmate(modelName, modelRoles);
    }

}
