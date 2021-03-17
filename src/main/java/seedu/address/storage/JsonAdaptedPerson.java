package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.insurance.InsurancePlanName;
import seedu.address.model.insurance.InsurancePremium;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String gender;
    private final String birthdate;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String meeting;
    private final String planName;
    private final String premium;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("gender") String gender, @JsonProperty("birthdate") String birthdate,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("meeting") String meeting, @JsonProperty("planName") String planName,
                             @JsonProperty("premium") String premium) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.birthdate = birthdate;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.meeting = meeting;
        this.planName = planName;
        this.premium = premium;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        gender = source.getGender().value;
        birthdate = source.getBirthdate().value.toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        meeting = source.getMeeting().map(x -> x.original).orElse(null);
        planName = source.getPlanName() == null ? "" : source.getPlanName().name;
        premium = source.getPremium() == null ? "" : source.getPremium().toString();
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

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (birthdate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Birthdate.class.getSimpleName()));
        }
        if (!Birthdate.isValidBirthdate(birthdate)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Birthdate modelBirthdate = new Birthdate(birthdate);

        final Optional<Meeting> modelMeeting;
        if (meeting == null) {
            modelMeeting = Optional.empty();
        } else {
            modelMeeting = Optional.of(meeting).map(Meeting::new);
        }

        final InsurancePlanName modelPlanName;
        if (planName.equals("")) {
            modelPlanName = null;
        } else {
            if (!InsurancePlanName.isValidName(planName)) {
                throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
            }
            modelPlanName = new InsurancePlanName(planName);
        }

        final InsurancePremium modelPremium;
        if (premium.equals("")) {
            modelPremium = null;
        } else {
            if (!InsurancePremium.isValidAmount(premium)) {
                throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
            }
            modelPremium = new InsurancePremium(premium);
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelGender, modelBirthdate,
                modelTags, modelMeeting, modelPlanName, modelPremium);
    }

}
