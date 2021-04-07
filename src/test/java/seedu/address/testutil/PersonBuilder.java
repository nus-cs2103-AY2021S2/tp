package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.person.level.Level;
import seedu.address.model.subject.Subject;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_SCHOOL = "Jurong West Secondary School";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GUARDIAN_NAME = "Ben Bee";
    public static final String DEFAULT_GUARDIAN_PHONE = "88886666";
    public static final String DEFAULT_LEVEL = "sec3";

    private Name name;
    private Phone phone;
    private Optional<School> school;
    private Optional<Email> email;
    private Optional<Address> address;
    private Optional<Name> guardianName;
    private Optional<Phone> guardianPhone;
    private Optional<Level> level;
    private Set<Subject> subjects;
    private Set<Lesson> lessons;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        school = Optional.of(new School(DEFAULT_SCHOOL));
        email = Optional.of(new Email(DEFAULT_EMAIL));
        address = Optional.of(new Address(DEFAULT_ADDRESS));
        guardianName = Optional.of(new Name(DEFAULT_GUARDIAN_NAME));
        guardianPhone = Optional.of(new Phone(DEFAULT_GUARDIAN_PHONE));
        level = Optional.of(new Level(DEFAULT_LEVEL));
        subjects = new HashSet<>();
        lessons = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        school = personToCopy.getSchool();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        guardianName = personToCopy.getGuardianName();
        guardianPhone = personToCopy.getGuardianPhone();
        level = personToCopy.getLevel();
        subjects = new HashSet<>(personToCopy.getSubjects());
        lessons = new HashSet<>(personToCopy.getLessons());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Parses the {@code subjects} into a {@code Set<Subject>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSubjects(String ... subjects) {
        this.subjects = SampleDataUtil.getSubjectSet(subjects);
        return this;
    }

    /**
     * Parses the {@code lessons} into a {@code Set<Lesson>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withLessons(String ... lessons) {
        this.lessons = SampleDataUtil.getLessonSet(lessons);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = Optional.of(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = Optional.of(new Email(email));
        return this;
    }

    /**
     * Sets the {@code School} of the {@code Person} that we are building.
     */
    public PersonBuilder withSchool(String school) {
        this.school = Optional.of(new School(school));
        return this;
    }

    /**
     * Sets the {@code GuardianName} of the {@code Person} that we are building.
     */
    public PersonBuilder withGuardianName(String name) {
        this.guardianName = Optional.of(new Name(name));
        return this;
    }

    /**
     * Sets the {@code GuardianPhone} of the {@code Person} that we are building.
     */
    public PersonBuilder withGuardianPhone(String phone) {
        this.guardianPhone = Optional.of(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Level} of the {@code Person} that we are building.
     */
    public PersonBuilder withLevel(String level) {
        this.level = Optional.of(new Level(level));
        return this;
    }

    /**
     * Builds the {@code Person}.
     */
    public Person build() {
        return new Person(name, phone, school, email,
                address, guardianName, guardianPhone, level, subjects, lessons);
    }

}
