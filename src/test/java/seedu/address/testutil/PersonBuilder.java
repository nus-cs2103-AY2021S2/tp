package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GENDER = "Female";
    public static final String DEFAULT_BIRTHDATE = "1990-01-01";
    public static final String DEFAULT_PLAN_NAME = "Protecc";
    public static final String DEFAULT_PREMIUM = "4800";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Gender gender;
    private Birthdate birthdate;
    private Set<Tag> tags;
    private Optional<Meeting> meeting;
    private InsurancePlanName planName;
    private InsurancePremium premium;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        gender = new Gender(DEFAULT_GENDER);
        birthdate = new Birthdate(DEFAULT_BIRTHDATE);
        tags = new HashSet<>();
        meeting = Optional.empty();
        planName = new InsurancePlanName(DEFAULT_PLAN_NAME);
        premium = new InsurancePremium(DEFAULT_PREMIUM);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        gender = personToCopy.getGender();
        birthdate = personToCopy.getBirthdate();
        tags = new HashSet<>(personToCopy.getTags());
        meeting = personToCopy.getMeeting();
        planName = personToCopy.getPlanName();
        premium = personToCopy.getPremium();
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
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Birthdate} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthdate(String birthdate) {
        this.birthdate = new Birthdate(birthdate);
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
     * Sets the {@code Meeting} of the {@code Person} that we are building.
     */
    public PersonBuilder withMeeting(String meeting) {
        this.meeting = Optional.ofNullable(new Meeting(meeting));
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, planName, premium);
    }

}
