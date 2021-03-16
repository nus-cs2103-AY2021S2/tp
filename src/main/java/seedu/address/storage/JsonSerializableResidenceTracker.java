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
import seedu.address.model.residence.Residence;

/**
 * An Immutable ResidenceTracker that is serializable to JSON format.
 */
@JsonRootName(value = "residencetracker")
class JsonSerializableResidenceTracker {

    public static final String MESSAGE_DUPLICATE_RESIDENCE = "Residences list contains duplicate residences(s).";

    private final List<JsonAdaptedResidence> residences = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableResidenceTracker} with the given residences.
     */
    @JsonCreator
    public JsonSerializableResidenceTracker(@JsonProperty("residences") List<JsonAdaptedResidence> residences) {
        this.residences.addAll(residences);
    }

    /**
     * Converts a given {@code ReadOnlyResidenceTracker} into this class for Json use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableResidenceTracker}.
     */
    public JsonSerializableResidenceTracker(ReadOnlyResidenceTracker source) {
        residences.addAll(source.getResidenceList()
                .stream().map(JsonAdaptedResidence::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ResidenceTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ResidenceTracker toModelType() throws IllegalValueException {
        ResidenceTracker residenceTracker = new ResidenceTracker();
        for (JsonAdaptedResidence jsonAdaptedResidence : residences) {
            Residence residence = jsonAdaptedResidence.toModelType();
            if (residenceTracker.hasResidence(residence)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RESIDENCE);
            }
            residenceTracker.addResidence(residence);
        }
        return residenceTracker;
    }

}
