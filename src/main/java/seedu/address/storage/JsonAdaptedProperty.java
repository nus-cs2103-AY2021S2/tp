package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.Client;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.DateTimeFormat;

/**
 * Jackson-friendly version of {@link Property}.
 */
class JsonAdaptedProperty {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Property's %s field is missing!";

    private final String name;
    private final String propertyType;
    private final String address;
    private final String postalCode;
    private final String deadline;
    private final String remark;
    private final String client;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProperty} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedProperty(@JsonProperty("name") String name, @JsonProperty("propertyType") String propertyType,
                               @JsonProperty("address") String address, @JsonProperty("remark") String remark,
                               @JsonProperty("postalCode") String postalCode,
                               @JsonProperty("deadline") String deadline, @JsonProperty("client") String client,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.remark = remark;
        this.propertyType = propertyType;
        this.address = address;
        this.postalCode = postalCode;
        this.deadline = deadline;
        this.client = client;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Property} into this class for Jackson use.
     */
    public JsonAdaptedProperty(Property source) {
        name = source.getName().toString();
        propertyType = source.getPropertyType().toString();
        address = source.getAddress().toString();
        postalCode = source.getPostalCode().toString();
        deadline = source.getDeadline().toString();

        if (source.getRemarks() != null) {
            remark = source.getRemarks().toString();
        } else {
            remark = null;
        }
        if (source.getClient() != null) {
            client = source.getClient().toString();
        } else {
            client = null;
        }
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted property object into the model's {@code Property} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public Property toModelType() throws IllegalValueException {
        final List<Tag> propertyTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            propertyTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(propertyTags);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (propertyType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Type.class.getSimpleName()));
        }
        if (!Type.isValidType(propertyType)) {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }
        final Type modelType = new Type(propertyType);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (postalCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PostalCode.class.getSimpleName()));
        }
        if (!PostalCode.isValidPostal(postalCode)) {
            throw new IllegalValueException(PostalCode.MESSAGE_CONSTRAINTS);
        }
        final PostalCode modelPostal = new PostalCode(postalCode);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        final Deadline modelDeadline = new Deadline(LocalDate.parse(deadline, DateTimeFormat.OUTPUT_DATE_FORMAT));

        if (remark == null && client == null) {
            return new Property(modelName, modelType, modelAddress, modelPostal, modelDeadline, modelTags);
        } else if (remark != null && client == null) {
            if (!Remark.isValidRemark(remark)) {
                throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
            }
            final Remark modelRemark = new Remark(remark);
            return new Property(modelName, modelType, modelAddress, modelPostal, modelDeadline, modelRemark,
                    modelTags);
        } else if (remark == null && client != null) {
            //TODO add test to validate client
            final Client modelCLient = Client.fromStringToClient(client);
            return new Property(modelName, modelType, modelAddress, modelPostal, modelDeadline, modelCLient,
                    modelTags);
        } else {
            if (!Remark.isValidRemark(remark)) {
                throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
            }
            final Remark modelRemark = new Remark(remark);
            final Client modelCLient = Client.fromStringToClient(client);
            return new Property(modelName, modelType, modelAddress, modelPostal, modelDeadline, modelRemark,
                    modelCLient, modelTags);
        }
    }
}

