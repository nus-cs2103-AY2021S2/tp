package dog.pawbook.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Jackson-friendly version of {@link Entity}.
 */
class JsonAdaptedEntity {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entities' %s field is missing!";

    private final String name;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final Map<String, String> propertyDict = new HashMap<>();

    /**
     * Constructs a {@code JsonAdaptedEntity} with the given owner details.
     */
    @JsonCreator
    public JsonAdaptedEntity(@JsonProperty("name") String name,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("propertyDict") Map<String, String> propertyDict) {
        this.name = name;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.propertyDict.putAll(propertyDict);
    }

    /**
     * Converts a given {@code Entity} into this class for Jackson use.
     */
    public JsonAdaptedEntity(Entity source) {
        name = source.getName().fullName;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        propertyDict.putAll(source.getOtherPropertiesAsDict());
    }

    /**
     * Converts this Jackson-friendly adapted entity object into the model's {@code Entity} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entity.
     */
    public Entity toModelType() throws IllegalValueException {
        final List<Tag> ownerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            ownerTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        final Set<Tag> modelTags = new HashSet<>(ownerTags);

        if (propertyDict == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "all other properties"));
        }

        if (!propertyDict.containsKey("type")) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "type"));
        }

        // todo: add dog into this and also extract some methods (might want to make Phone, Email etc have common base)
        switch (propertyDict.get("type")) {
        case Owner.ENTITY_WORD:
            if (!propertyDict.containsKey(Phone.class.getSimpleName())
                    || propertyDict.get(Phone.class.getSimpleName()) == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
            }

            String phone = propertyDict.get(Phone.class.getSimpleName());
            if (!Phone.isValidPhone(phone)) {
                throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
            }
            final Phone modelPhone = new Phone(phone);

            if (!propertyDict.containsKey(Email.class.getSimpleName())
                    || propertyDict.get(Email.class.getSimpleName()) == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
            }

            String email = propertyDict.get(Email.class.getSimpleName());
            if (!Email.isValidEmail(email)) {
                throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
            }
            final Email modelEmail = new Email(email);

            if (!propertyDict.containsKey(Address.class.getSimpleName())
                    || propertyDict.get(Address.class.getSimpleName()) == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
            }

            String address = propertyDict.get(Address.class.getSimpleName());
            if (!Address.isValidAddress(address)) {
                throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
            }
            final Address modelAddress = new Address(address);

            return new Owner(modelName, modelPhone, modelEmail, modelAddress, modelTags);

        default:
            throw new IllegalValueException("Invalid entity type!");
        }
    }
}
