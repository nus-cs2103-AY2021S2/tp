package seedu.address.storage;

import java.time.LocalDate;

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
import seedu.address.model.util.DateTimeFormat;

/**
 * Jackson-friendly version of {@link Property}.
 */
class JsonAdaptedProperty {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String name;
    private final String propertyType;
    private final String address;
    private final String postalCode;
    private final String deadline;
    private final String remark;
    private final String client;

    /**
     * Constructs a {@code JsonAdaptedProperty} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedProperty(@JsonProperty("name") String name, @JsonProperty("propertyType") String propertyType,
                               @JsonProperty("address") String address, @JsonProperty("remark") String remark,
                               @JsonProperty("postalCode") String postalCode,
                               @JsonProperty("deadline") String deadline, @JsonProperty("client") String client){
        this.name = name;
        this.remark = remark;
        this.propertyType = propertyType;
        this.address = address;
        this.postalCode = postalCode;
        this.deadline = deadline;
        this.client = client;
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
    }

    /**
     * Converts this Jackson-friendly adapted property object into the model's {@code Property} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public Property toModelType() throws IllegalValueException {

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
            return new Property(modelName, modelType, modelAddress, modelPostal, modelDeadline);
        } else if (remark != null && client == null) {
            if (!Remark.isValidRemark(remark)) {
                throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
            }
            final Remark modelRemark = new Remark(remark);
            return new Property(modelName, modelType, modelAddress, modelPostal, modelDeadline, modelRemark);
        } else if (remark == null && client != null) {
                //TODO add test to validate client
            final Client modelCLient = Client.fromStringToClient(client);
            return new Property(modelName, modelType, modelAddress, modelPostal, modelDeadline, modelCLient);
        } else {
            if (!Remark.isValidRemark(remark)) {
                throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
            }
            final Remark modelRemark = new Remark(remark);
            final Client modelCLient = Client.fromStringToClient(client);
            return new Property(modelName, modelType, modelAddress, modelPostal, modelDeadline, modelRemark,
                    modelCLient);
        }
    }
}

