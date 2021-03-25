package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Event;
import seedu.address.model.person.Goal;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Picture;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String birthday;
    private final String goal;

    private final String address;
    private final JsonAdaptedPicture picture;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedEvent> dates = new ArrayList<>();
    private final List<JsonAdaptedEvent> meetings = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("birthday") String birthday,
            @JsonProperty("goal") String goal, @JsonProperty("address") String address,
            @JsonProperty("picture") JsonAdaptedPicture picture, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("dates") List<JsonAdaptedEvent> dates,
            @JsonProperty("meetings") List<JsonAdaptedEvent> meetings) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.goal = goal;
        this.address = address;
        this.picture = picture;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (dates != null) {
            this.dates.addAll(dates);
        }
        if (meetings != null) {
            this.meetings.addAll(meetings);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        birthday = source.getBirthday().toString();
        System.out.println(name);
        goal = source.getGoal().toString();

        Optional<Picture> srcPic = source.getPicture();
        picture = srcPic.isEmpty() ? null : new JsonAdaptedPicture(srcPic.get());

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        dates.addAll(source.getDates().stream()
                .map(JsonAdaptedEvent::new)
                .collect(Collectors.toList()));
        meetings.addAll(source.getMeetings().stream()
                .map(JsonAdaptedEvent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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

        if (birthday == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Birthday.class.getSimpleName()));
        }
        if (!Birthday.isValidBirthday(birthday)) {
            throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
        }
        final Birthday modelBirthday = new Birthday(birthday);

        if (!Goal.isValidGoal(goal)) {
            throw new IllegalValueException(Goal.MESSAGE_CONSTRAINTS);
        }
        final Goal modelGoal = new Goal(Goal.ENUM_MAP.get(goal.toLowerCase(Locale.ROOT)));

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        Picture modelPicture = null;
        if (picture != null) {
            modelPicture = picture.toModelType();
        }

        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(personTags);

        final List<Event> modelDates = new ArrayList<>();
        for (JsonAdaptedEvent date : dates) {
            modelDates.add(date.toModelType());
        }

        final List<Event> modelMeetings = new ArrayList<>();
        for (JsonAdaptedEvent meeting : meetings) {
            modelMeetings.add(meeting.toModelType());
        }

        return new Person(modelName, modelPhone, modelEmail, modelBirthday, modelGoal, modelAddress, modelPicture,
                modelTags, modelDates, modelMeetings);
    }
}
