package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Contact}.
 */
class JsonAdaptedContact {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Contact's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedContact} with the given contact details.
     */
    @JsonCreator
    public JsonAdaptedContact(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Contact toModelType() throws IllegalValueException {
        final List<Tag> contactTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            contactTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactName.class.getSimpleName()));
        }
        if (!ContactName.isValidName(name)) {
            throw new IllegalValueException(ContactName.MESSAGE_CONSTRAINTS);
        }
        final ContactName modelName = new ContactName(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactPhone.class.getSimpleName()));
        }
        if (!ContactPhone.isValidPhone(phone)) {
            throw new IllegalValueException(ContactPhone.MESSAGE_CONSTRAINTS);
        }
        final ContactPhone modelPhone = new ContactPhone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactEmail.class.getSimpleName()));
        }
        if (!ContactEmail.isValidEmail(email)) {
            throw new IllegalValueException(ContactEmail.MESSAGE_CONSTRAINTS);
        }
        final ContactEmail modelEmail = new ContactEmail(email);

        final Set<Tag> modelTags = new HashSet<>(contactTags);
        return new Contact(modelName, modelPhone, modelEmail, modelTags);
    }

}
