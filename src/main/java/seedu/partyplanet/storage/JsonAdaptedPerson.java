package seedu.partyplanet.storage;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.partyplanet.commons.exceptions.IllegalValueException;
import seedu.partyplanet.model.date.Date;
import seedu.partyplanet.model.person.Address;
import seedu.partyplanet.model.person.Birthday;
import seedu.partyplanet.model.person.Email;
import seedu.partyplanet.model.person.Name;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.person.Phone;
import seedu.partyplanet.model.person.Remark;
import seedu.partyplanet.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String birthday;
    private final String address;
    private final String remark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("birthday") String birthday,
            @JsonProperty("address") String address, @JsonProperty("remark") String remark,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
        this.remark = remark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        birthday = source.getBirthday().value;
        address = source.getAddress().value;
        remark = source.getRemark().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        Phone modelPhone;
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (phone.equals(Phone.EMPTY_PHONE_STRING)) {
            modelPhone = Phone.EMPTY_PHONE;
        } else if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        } else {
            modelPhone = new Phone(phone);
        }

        Email modelEmail;
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (email.equals(Email.EMPTY_EMAIL_STRING)) {
            modelEmail = Email.EMPTY_EMAIL;
        } else if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        } else {
            modelEmail = new Email(email);
        }

        Birthday modelBirthday;
        if (birthday == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName()));
        }
        if (birthday.equals(Date.EMPTY_DATE_STRING)) {
            modelBirthday = Birthday.EMPTY_BIRTHDAY;
        } else {
            try {
                modelBirthday = new Birthday(birthday);
            } catch (DateTimeException err) { // date in wrong format
                throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
            } catch (IllegalArgumentException err) { // birthday year exceeds current year
                throw new IllegalValueException(Birthday.MESSAGE_BIRTHDAY_CONSTRAINTS);
            }
        }

        Address modelAddress;
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (address.equals(Address.EMPTY_ADDRESS_STRING)) {
            modelAddress = Address.EMPTY_ADDRESS;
        } else if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        } else {
            modelAddress = new Address(address);
        }

        Remark modelRemark;
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (remark.equals(Remark.EMPTY_REMARK_STRING)) {
            modelRemark = Remark.EMPTY_REMARK;
        } else if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        } else {
            modelRemark = new Remark(remark);
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelBirthday, modelAddress, modelRemark, modelTags);
    }

}
