package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_SCHOOL = "Jurong West Secondary School";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GUARDIAN_NAME = "Ben Bee";
    public static final String DEFAULT_GUARDIAN_PHONE = "88886666";

    private Name name;
    private School school;
    private Phone phone;
    private Email email;
    private Address address;
    private Name guardianName;
    private Phone guardianPhone;
    private Set<Tag> tags;
    private Set<Lesson> lessons;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        school = new School(DEFAULT_SCHOOL);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        guardianName = new Name(DEFAULT_GUARDIAN_NAME);
        guardianPhone = new Phone(DEFAULT_GUARDIAN_PHONE);
        tags = new HashSet<>();
        lessons = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        school = personToCopy.getSchool();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        guardianName = personToCopy.getGuardianName();
        guardianPhone = personToCopy.getGuardianPhone();
        tags = new HashSet<>(personToCopy.getTags());
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
        this.address = new Address(address);
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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code School} of the {@code Person} that we are building.
     */
    public PersonBuilder withSchool(String school) {
        this.school = new School(school);
        return this;
    }

    /**
     * Sets the {@code GuardianName} of the {@code Person} that we are building.
     */
    public PersonBuilder withGuardianName(String name) {
        this.guardianName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code GuardianPhone} of the {@code Person} that we are building.
     */
    public PersonBuilder withGuardianPhone(String phone) {
        this.guardianPhone = new Phone(phone);
        return this;
    }

    /**
     * Builds the {@code Person}.
     */
    public Person build() {
        return new Person(name, school, phone, email,
                address, guardianName, guardianPhone, tags, lessons);
    }

}
