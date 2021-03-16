package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.plan.Module;
import seedu.address.model.plan.Semester;

/**
 * Jackson-friendly version of {@link Semester}.
 */
class JsonAdaptedSemester {

    private final int semNumber;
    private final List<JsonAdaptedModule> modules = new ArrayList<>();
    /**
     * Constructs a {@code JsonAdaptedSemester} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedSemester(@JsonProperty("semNumber") int semNumber,
                               @JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.semNumber = semNumber;
        if (modules != null) {
            this.modules.addAll(modules);
        }
    }

    /**
     * Converts a given {@code Semester} into this class for Jackson use.
     */
    public JsonAdaptedSemester(Semester source) {
        semNumber = source.getSemNumber();
        modules.addAll(source.getModules().stream()
                .map(JsonAdaptedModule::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Semester toModelType() throws IllegalValueException {
        final List<Module> normModules = new ArrayList<>();
        for (JsonAdaptedModule m : this.modules) {
            normModules.add(m.toModelType());
        }
        return new Semester(semNumber, normModules);
    }
}
