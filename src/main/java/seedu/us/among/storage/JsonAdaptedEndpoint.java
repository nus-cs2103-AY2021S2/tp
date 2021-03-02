package seedu.us.among.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.us.among.commons.exceptions.IllegalValueException;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Name;
import seedu.us.among.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Endpoint}.
 */
class JsonAdaptedEndpoint {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Endpoint's %s field is missing!";

    private final String name;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEndpoint} with the given endpoint details.
     */
    @JsonCreator
    public JsonAdaptedEndpoint(@JsonProperty("name") String name,
                               @JsonProperty("address") String address,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Endpoint} into this class for Jackson use.
     */
    public JsonAdaptedEndpoint(Endpoint source) {
        name = source.getName().fullName;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted endpoint object into the model's {@code Endpoint} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted endpoint.
     */
    public Endpoint toModelType() throws IllegalValueException {
        final List<Tag> endpointTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            endpointTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(endpointTags);
        return new Endpoint(modelName, modelAddress, modelTags);
    }

}
