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
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.person.level.Level;
import seedu.address.model.subject.Subject;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String school;
    private final String email;
    private final String address;
    private final String guardianName;
    private final String guardianPhone;
    private final String level;
    private final List<JsonAdaptedSubject> subjects = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("school") String school, @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("guardianName") String guardianName,
                             @JsonProperty("guardianPhone") String guardianPhone,
                             @JsonProperty("level") String level,
                             @JsonProperty("subjects") List<JsonAdaptedSubject> subjects,
                             @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.name = name;
        this.phone = phone;
        this.school = school;
        this.email = email;
        this.address = address;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.level = level;
        if (subjects != null) {
            this.subjects.addAll(subjects);
        }
        if (lessons != null) {
            this.lessons.addAll(lessons);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        if (source.getSchool().isPresent()) {
            school = source.getSchool().get().fullSchoolName;
        } else {
            school = "";
        }
        if (source.getEmail().isPresent()) {
            email = source.getEmail().get().value;
        } else {
            email = "";
        }
        if (source.getAddress().isPresent()) {
            address = source.getAddress().get().value;
        } else {
            address = "";
        }
        if (source.getGuardianName().isPresent()) {
            guardianName = source.getGuardianName().get().fullName;
        } else {
            guardianName = "";
        }
        if (source.getGuardianPhone().isPresent()) {
            guardianPhone = source.getGuardianPhone().get().value;
        } else {
            guardianPhone = "";
        }
        if (source.getLevel().isPresent()) {
            level = source.getLevel().get().getLevel();
        } else {
            level = "";
        }
        subjects.addAll(source.getSubjects().stream()
                .map(JsonAdaptedSubject::new)
                .collect(Collectors.toList()));
        lessons.addAll(source.getLessons().stream()
                .map(JsonAdaptedLesson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Subject> personSubjects = new ArrayList<>();
        for (JsonAdaptedSubject tag : subjects) {
            personSubjects.add(tag.toModelType());
        }
        final List<Lesson> personLessons = new ArrayList<>();
        for (JsonAdaptedLesson lesson: lessons) {
            personLessons.add(lesson.toModelType());
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

        if (school == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, School.class.getSimpleName()));
        }
        if (!school.equals("") && !School.isValidSchool(school)) {
            throw new IllegalValueException(School.MESSAGE_CONSTRAINTS);
        }
        final Optional<School> modelSchool = school.equals("") ? Optional.empty() : Optional.of(new School(school));

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!email.equals("") && !Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Optional<Email> modelEmail = email.equals("") ? Optional.empty() : Optional.of(new Email(email));

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!address.equals("") && !Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Optional<Address> modelAddress = address.equals("") ? Optional.empty()
                : Optional.of(new Address(address));

        if (guardianName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!guardianName.equals("") && !Name.isValidName(guardianName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Optional<Name> modelGuardianName = guardianName.equals("") ? Optional.empty()
                : Optional.of(new Name(guardianName));

        if (guardianPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!guardianPhone.equals("") && !Phone.isValidPhone(guardianPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Optional<Phone> modelGuardianPhone = guardianPhone.equals("") ? Optional.empty()
                : Optional.of(new Phone(guardianPhone));
        if (level == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Level.class.getSimpleName()));
        }
        if (!level.equals("") && !Level.isValidLevel(level)) {
            throw new IllegalValueException(Level.MESSAGE_CONSTRAINTS);
        }
        final Optional<Level> modelLevel = level.equals("") ? Optional.empty()
                : Optional.of(new Level(level));

        final Set<Subject> modelSubjects = new HashSet<>(personSubjects);
        final Set<Lesson> modelLessons = new HashSet<>(personLessons);
        return new Person(modelName, modelPhone, modelSchool, modelEmail, modelAddress, modelGuardianName,
                modelGuardianPhone, modelLevel, modelSubjects, modelLessons);
    }

}
