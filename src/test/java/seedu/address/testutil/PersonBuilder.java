package seedu.address.testutil;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opentest4j.TestAbortedException;

import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Debt;
import seedu.address.model.person.Email;
import seedu.address.model.person.Goal;
import seedu.address.model.person.Goal.Frequency;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Picture;
import seedu.address.model.person.SpecialDate;
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
    public static final String DEFAULT_BIRTHDAY = "02-03-1995";
    public static final Frequency DEFAULT_GOAL = Frequency.NONE;

    private Name name;
    private Phone phone;
    private Email email;
    private Birthday birthday;
    private Goal goal;
    private Address address;
    private Picture picture;
    private Debt debt;
    private Set<Tag> tags;
    private List<SpecialDate> dates;
    private List<Meeting> meetings;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        goal = new Goal(DEFAULT_GOAL);
        address = new Address(DEFAULT_ADDRESS);
        picture = null;
        debt = new Debt("0");
        tags = new HashSet<>();
        dates = new ArrayList<>();
        meetings = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        birthday = personToCopy.getBirthday();
        goal = personToCopy.getGoal();
        address = personToCopy.getAddress();
        picture = personToCopy.getPicture().orElse(null);
        debt = personToCopy.getDebt();
        tags = new HashSet<>(personToCopy.getTags());
        dates = new ArrayList<>(personToCopy.getDates());
        meetings = new ArrayList<>(personToCopy.getMeetings());
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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
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
     * Sets the {@code Debt} of the {@code Person} that we are building.
     */
    public PersonBuilder withDebt(String debt) {
        this.debt = new Debt(debt);
        return this;
    }

    /**
     * Sets the {@code Debt} of the {@code Person} that we are building.
     * Overloaded method for testing purposes only.
     */
    public PersonBuilder withDebt(Debt debt) {
        this.debt = debt;
        return this;
    }

    /**
     * Parses the {@code path} into a {@code Picture} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withPicture(Path path) {
        if (!Picture.isValidFilePath(path)) {
            throw new TestAbortedException(String.format("%s is invalid. %s", path, Picture.MESSAGE_CONSTRAINTS));
        }

        this.picture = new Picture(path);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code meetings} into a {@code List<Meeting>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withMeetings(Meeting... meetings) {
        this.meetings = SampleDataUtil.getMeetingList(meetings);
        return this;
    }

    /**
     * Parses the {@code dates} into a {@code List<SpecialDate>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withDates(SpecialDate... dates) {
        this.dates = SampleDataUtil.getDateList(dates);
        return this;
    }

    /**
     * Builds the person with the information within the PersonBuilder.
     * @return Person object
     */
    public Person build() {
        return new Person(name, phone, email, birthday, goal, address,
                picture, debt, tags, dates, meetings);
    }
}
