package dog.pawbook.storage;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.tag.Tag;
import javafx.util.Pair;

@JsonTypeName("owner")
public class JsonAdaptedOwner extends JsonAdaptedEntity {
    private final String phone;
    private final String email;
    private final String address;

    /**
     * Constructs a {@code JsonAdaptedEntity} with the given owner details.
     */
    @JsonCreator
    public JsonAdaptedOwner(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
            @JsonProperty("phone") String phone, @JsonProperty("email") String email,
            @JsonProperty("address") String address, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(id, name, tagged);
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Converts a given {@code Entity} into this class for Jackson use.
     */
    public JsonAdaptedOwner(Pair<Integer, Owner> idOwnerPair) {
        super(idOwnerPair);
        Owner source = idOwnerPair.getValue();
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
    }

    /**
     * Converts this Jackson-friendly adapted owner object into the model's {@code Owner} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entity.
     */
    @Override
    public Pair<Integer, Entity> toModelType() throws IllegalValueException {
        CommonAttributes commonAttributes = checkAndGetCommonAttributes();
        final int modelID = commonAttributes.id;
        final Name modelName = commonAttributes.name;
        final Set<Tag> modelTags = commonAttributes.tags;

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        Owner model = new Owner(modelName, modelPhone, modelEmail, modelAddress, modelTags);

        return new Pair<>(modelID, model);
    }
}
