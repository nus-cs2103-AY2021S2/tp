package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.plan.Description;
import seedu.address.model.plan.Plan;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Plan}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Plan's %s field is missing!";
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given plan details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Plan} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Plan source) {
        address = source.getDescription().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted plan object into the model's {@code Plan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted plan.
     */
    public Plan toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Description.class.getSimpleName()));
        }
        if (!Description.isValidAddress(address)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Plan(modelDescription, modelTags);
    }

}
