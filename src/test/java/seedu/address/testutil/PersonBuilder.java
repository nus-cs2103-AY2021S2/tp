package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.Tutor;
import seedu.address.model.session.SessionId;
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
    public static final String DEFAULT_PERSON_TYPE = "student";
    public static final String DEFAULT_PERSON_ID = "s/1";


    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private PersonType personType;
    private PersonId personId;
    private List<SessionId> sessions;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        personType = new PersonType(DEFAULT_PERSON_TYPE);
        personId = new PersonId(DEFAULT_PERSON_ID);
        sessions = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        personType = personToCopy.getPersonType();
        personId = personToCopy.getPersonId();
        sessions = personToCopy.getSessions();
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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }
    /**
     * Sets the {@code PersonType} of the {@code Person} that we are building.
     */
    public PersonBuilder withPersonType(String type) {
        this.personType = new PersonType(type);
        return this;
    }

    /**
     * Sets the {@code PersonId} of the {@code Person} that we are building.
     */
    public PersonBuilder withPersonId(String id) {
        this.personId = new PersonId(id);
        return this;
    }

    /**
     * Parses the {@code sessionIds} into a {@code List<SessionId>} and set it to the {@code Person}
     * that we are building.
     * @param sessionId
     * @return
     */
    public PersonBuilder withSessions(String ... sessionId) {
        this.sessions = SampleDataUtil.getSessionIds();
        return this;
    }

    /**
     * Build
     * @return Person
     */
    public Person build() {
        if (this.personType.toString().equals("student")) {
            Person student = new Student(personId, name, phone, email, address, tags);
            student.getSessions().addAll(sessions);
            return student;
        } else {
            Person tutor = new Tutor(personId, name, phone, email, address, tags);
            tutor.getSessions().addAll(sessions);
            return tutor;
        }
    }

}
